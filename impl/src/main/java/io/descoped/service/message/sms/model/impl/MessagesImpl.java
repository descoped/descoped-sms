/***************************************************************************
 * Copyright 2003-2004 Ove Ranheim         All rights reserved.  *
 * Please look at license.txt in info directory for more license detail.   *
 **************************************************************************/
package io.descoped.service.message.sms.model.impl;

import io.descoped.service.message.sms.model.LogonStatus;
import io.descoped.service.message.sms.model.Message;
import io.descoped.service.message.sms.model.Messages;
import io.descoped.service.message.sms.model.Recipient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author: Ove Ranheim
 * @email: oranheim@descoped.io
 */
public class MessagesImpl extends ArrayList<Message> implements Serializable, Messages {

    private static final long serialVersionUID = 1L;

    private LogonStatus logonStatus;
    private String encoding;
    private String reason;
    private boolean gotError;

    public MessagesImpl() {
        clear();
    }

    public void clear() {
        super.clear();
        logonStatus = LogonStatus.NONE;
        encoding = "UTF-8";
        reason = "";
        gotError = false;
    }

    public LogonStatus getLogonStatus() {
        return logonStatus;
    }

    public void setLogonStatus(LogonStatus logonStatus) {
        this.logonStatus = logonStatus;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean hasErrorOccured() {
        return gotError;
    }

    public void errorOccured() {
        gotError = true;
    }

    public Iterator<Message> iterator() {
        return super.iterator();
    }

    public Message getMessage(int index) {
        return (Message) get(index);
    }

    public Message addMessage() {
        Message message = new MessageImpl();
        add(message);
        return message;
    }

    public Message addMessage(Message message) {
        add(message);
        return message;
    }

    public void removeMessage(Message message) {
        remove(message);
    }

    public Message findMessageById(Integer id) {
        for (Iterator<Message> i = iterator(); i.hasNext(); ) {
            Message m = i.next();
            for (Iterator<Recipient> j = m.getRecipients().iterator(); j.hasNext(); ) {
                Recipient r = j.next();
                if (id.equals(r.getId())) {
                    return m;
                }
            }
        }
        return null;
    }

    public int count() {
        return size();
    }

}
