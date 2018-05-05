/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.model.impl;

import io.descoped.service.message.sms.model.MessageStatus;
import io.descoped.service.message.sms.model.Recipient;

import java.io.Serializable;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 18, 2004 3:52:33 PM
 */
public class RecipientImpl implements Serializable, Recipient {

    private static final long serialVersionUID = 1L;

    public static Integer identity = new Integer(0);
    private Integer id;
    private String to;
    private MessageStatus status = MessageStatus.DRAFT;
    private String error = "";

    public RecipientImpl(String to) {
        this.to = to;
        synchronized (identity) {
            int id = identity.intValue();
            id++;
            this.id = identity = new Integer(id);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getTo() {
        return to;
    }

    public Recipient setTo(String to) {
        this.to = to;
        return this;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public Recipient setStatus(MessageStatus status) {
        this.status = status;
        return this;
    }

    public String getError() {
        return error;
    }

    public Recipient setError(String error) {
        this.error = error;
        return this;
    }

    @Override
    public String toString() {
        return "RecipientImpl{" +
                "id=" + id +
                ", to='" + to + '\'' +
                ", status=" + status +
                ", error='" + error + '\'' +
                '}';
    }
}

