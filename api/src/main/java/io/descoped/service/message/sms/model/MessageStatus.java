/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.model;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 18, 2004 3:56:19 PM
 */
public enum MessageStatus {
    DRAFT("DRAFT"),
    PENDING("PENDING"),
    SENDING("SENDING"),
    COMPLETE("COMPLETE"),
    FAILURE("FAILURE");

    private String longName;

    private MessageStatus(String longName) {
        this.setLongName(longName);
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

}
