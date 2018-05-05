/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.model;

import io.descoped.service.message.sms.encoder.MessageFormat;

import java.util.List;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 17, 2004 4:05:27 PM
 */
public interface Message {

    public String getFrom();

    public Message setFrom(String from);

    public List<Recipient> getRecipients();

    public Recipient addRecipient(Recipient recipient);

    public Recipient addRecipient(String recipient);

    public void removeRecipient(Recipient recipient);

    public void removeRecipient(int index);

    public Recipient findRecipientById(Integer id);

    public void clearRecipients();

    public int countRecipients();

    public Object getContent();

    public Message setContent(Object content);

    public MessageFormat getFormat();

    public Message setFormat(MessageFormat format);

}
