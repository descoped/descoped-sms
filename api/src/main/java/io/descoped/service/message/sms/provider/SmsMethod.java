/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.provider;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jul 6, 2004 10:00:11 AM
 */
final public class SmsMethod {

    final static public SmsMethod SOCKET_CLIENT = new SmsMethod("SOCKET_CLIENT");
    final static public SmsMethod HTTP_CLIENT = new SmsMethod("HTTP_CLIENT");

    private String name;

    public SmsMethod(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

}
