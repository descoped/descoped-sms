/***************************************************************************
 * Copyright 2003-2004 Ove Ranheim         All rights reserved.  *
 * Please look at license.txt in info directory for more license detail.   *
 **************************************************************************/
package io.descoped.service.message.sms.model.impl;

import io.descoped.service.message.sms.encoder.MessageFormat;
import io.descoped.service.message.sms.model.Message;
import io.descoped.service.message.sms.model.Recipient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author: Ove Ranheim
 * @email: oranheim@descoped.io
 */
public class MessageImpl implements Serializable, Message {

    private static final long serialVersionUID = 1L;

    private String from = "";
    private List<Recipient> recipients = new ArrayList<Recipient>();
    private Object content = "";
    private MessageFormat format;

    public MessageImpl() {
    }

    public String getFrom() {
        return from;
    }

    public Message setFrom(String from) {
        this.from = from;
        return this;
    }

    public List<Recipient> getRecipients() {
        return recipients;
    }

    public Recipient addRecipient(Recipient recipient) {
        recipients.add(recipient);
        return recipient;
    }

    public Recipient addRecipient(String recipient) {
        Recipient r = new RecipientImpl(recipient);
        recipients.add(r);
        return r;
    }

    public void removeRecipient(Recipient recipient) {
        recipients.remove(recipient);
    }

    public void removeRecipient(int index) {
        recipients.remove(index);
    }

    public Recipient findRecipientById(Integer id) {
        for (Iterator<Recipient> i = recipients.iterator(); i.hasNext(); ) {
            Recipient r = i.next();
            if (id.equals(r.getId())) {
                return r;
            }
        }
        return null;
    }

    public void clearRecipients() {
        recipients.clear();
    }

    public int countRecipients() {
        return recipients.size();
    }

    public Object getContent() {
        return content;
    }

    public Message setContent(Object content) {
        this.content = content;
        return this;
    }

    public MessageFormat getFormat() {
        return format;
    }

    public Message setFormat(MessageFormat format) {
        this.format = format;
        return this;
    }

}

