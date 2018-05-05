/***************************************************************************
 * Copyright 2003-2004 Ove Ranheim         All rights reserved.  *
 * Please look at license.txt in info directory for more license detail.   *
 **************************************************************************/
package io.descoped.service.message.sms.impl;

import io.descoped.service.message.sms.SmsService;
import io.descoped.service.message.sms.common.CommunicationError;
import io.descoped.service.message.sms.common.ConvertException;
import io.descoped.service.message.sms.common.RequestException;
import io.descoped.service.message.sms.common.ResponseException;
import io.descoped.service.message.sms.model.Message;
import io.descoped.service.message.sms.model.Messages;
import io.descoped.service.message.sms.model.Recipient;
import io.descoped.service.message.sms.model.impl.MessageImpl;
import io.descoped.service.message.sms.model.impl.MessagesImpl;
import io.descoped.service.message.sms.model.impl.RecipientImpl;
import io.descoped.service.message.sms.provider.Provider;
import io.descoped.service.message.sms.provider.Sender;
import io.descoped.service.message.sms.provider.SenderImpl;
import io.descoped.service.message.sms.provider.impl.ProdatProviderImpl;

/**
 * @author: Ove Ranheim
 * @email: oranheim@descoped.io
 */
public class SmsServiceImpl implements SmsService {

    public SmsServiceImpl() {
    }

    /*
     * @see sedna.services.communication.service.SmsService#createProdatProvider(java.lang.String,
     *      java.lang.String)
     */
    public Provider createProdatProvider(String username, String password) {
        Provider provider = new ProdatProviderImpl();
        provider.getOperator().setUsername(username);
        provider.getOperator().setPassword(password);
        return provider;
    }

    /*
     * @see sedna.services.communication.service.SmsService#createMessages()
     */
    public Messages createMessages() {
        return new MessagesImpl();
    }

    /*
     * @see sedna.services.communication.service.SmsService#createMessaage()
     */
    public Message createMessage() {
        return new MessageImpl();
    }

    /*
     * @see sedna.services.communication.service.SmsService#createRecipient()
     */
    public Recipient createRecipient(String to) {
        return new RecipientImpl(to);
    }

    /*
     * @see sedna.services.communication.service.SmsService#getSender()
     */
    public Sender createSender(Provider provider) {
        Sender sender = new SenderImpl(provider);
        return sender;
    }

    /*
     * @see sedna.services.communication.service.SmsService#sendSms(sedna.services.communication.service.provider.Provider,
     *      sedna.services.communication.service.unit.Messages)
     */
    public boolean sendSms(Provider provider, Messages messages) throws CommunicationError, RequestException, ResponseException, ConvertException {
        Sender sender = new SenderImpl(provider);
        sender.prepare(messages);
        sender.send();
        return messages.hasErrorOccured();
    }

}