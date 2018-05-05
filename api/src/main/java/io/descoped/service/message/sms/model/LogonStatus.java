/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.model;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 20, 2004 1:26:10 PM
 */
public enum LogonStatus {
    NONE("None"),
    SUCCESS("Success"),
    FAILED("Failed");

    private String longName;

    private LogonStatus(String longName) {
        this.setLongName(longName);
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

}
