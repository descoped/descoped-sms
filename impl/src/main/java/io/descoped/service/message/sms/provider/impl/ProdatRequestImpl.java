/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.provider.impl;

import io.descoped.service.message.sms.common.ConvertException;
import io.descoped.service.message.sms.common.RequestException;
import io.descoped.service.message.sms.encoder.Formatter;
import io.descoped.service.message.sms.encoder.Resolver;
import io.descoped.service.message.sms.model.Message;
import io.descoped.service.message.sms.model.MessageStatus;
import io.descoped.service.message.sms.model.Messages;
import io.descoped.service.message.sms.model.Recipient;
import io.descoped.service.message.sms.provider.Operator;
import io.descoped.service.message.sms.provider.Provider;
import io.descoped.service.message.sms.provider.SmsRequest;
import io.descoped.service.message.sms.util.SmsUtil;

import java.util.Iterator;


/**
 * https://wiki.pswin.com/Gateway%20XML%20API.ashx#XML_examples_17
 *
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 17, 2004 9:39:05 PM
 */
public class ProdatRequestImpl implements SmsRequest {

    private Provider provider;
    private Messages messages;
    private String payload;

    public ProdatRequestImpl(Provider provider) {
        this.provider = provider;
    }

    /**
     * @return Returns the messages.
     */
    public Messages getMessages() {
        return messages;
    }

    /**
     * @param messages The messages to set.
     */
    public void setMessages(Messages messages) {
        this.messages = messages;
    }

    /**
     * @return Returns the payload.
     */
    public String getPayload() {
        return payload;
    }

    /**
     * @param payload The payload to set.
     */
    public void setPayload(String payload) {
        this.payload = payload;
    }

    /*
     * @see io.descoped.service.message.sms.provider.SenderImpl#prepare(Messages messages)
     * @see io.descoped.service.message.sms.provider.SmsRequest#prepare()
     */
    public void prepare() throws RequestException, ConvertException {
        Operator operator = provider.getOperator();
        StringBuffer buffer = new StringBuffer();
        buffer.append("<?xml version=\"1.0\" encoding=\"" + messages.getEncoding() + "\"?>\n");
        buffer.append("<!DOCTYPE SESSION SYSTEM \"pswincom_submit.dtd\">\n");
        buffer.append("<SESSION>\n");
        buffer.append("<CLIENT>" + operator.getUsername() + "</CLIENT>\n");
        buffer.append("<PW>" + operator.getPassword() + "</PW>\n");
        if (operator.getAffiliateProgram() != null)
            buffer.append(String.format("<AP>%s</AP>\n", operator.getAffiliateProgram()));
        //buffer.append("<SD></SD>\n"); // Session Data Code
        buffer.append("<MSGLST>\n");
        for (Iterator<Message> i = messages.iterator(); i.hasNext(); ) {
            Message message = i.next();
            String text = message.getContent().toString();
            Resolver resolver = provider.getResolver();
            Formatter formatter = resolver.getFormatter(message.getFormat());
            text = (String) formatter.convert(text);
            Integer op = (Integer) resolver.getOperationCode(message.getFormat());
            for (Iterator<Recipient> j = message.getRecipients().iterator(); j.hasNext(); ) {
                Recipient recipient = j.next();
                String from = message.getFrom();
                // Only consider numbers with + and 00 prefix and non alpha numbers as real number
                if (!SmsUtil.isAlpha(from) || SmsUtil.hasMobileNumberPrefix(from))
                    from = SmsUtil.prepareCellularNumber(from);
                buffer.append("<MSG>\n");
                buffer.append("<ID>" + recipient.getId() + "</ID>\n");
                buffer.append("<TEXT>" + text + "</TEXT>\n");
                buffer.append("<RCV>" + SmsUtil.prepareCellularNumber(recipient.getTo()) + "</RCV>\n");
                buffer.append("<SND>" + from + "</SND>\n");
                buffer.append("<OP>" + op.toString() + "</OP>\n");
                buffer.append("</MSG>\n");
                recipient.setStatus(MessageStatus.PENDING);
            }
        }
        buffer.append("</MSGLST>\n");
        buffer.append("</SESSION>\n");
        payload = buffer.toString();
    }

}
