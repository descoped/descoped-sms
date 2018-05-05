/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.model;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 18, 2004 4:07:00 PM
 */
public interface Recipient {

    public Integer getId();

    public String getTo();

    public Recipient setTo(String to);

    public MessageStatus getStatus();

    public Recipient setStatus(MessageStatus status);

    public String getError();

    public Recipient setError(String error);

}

