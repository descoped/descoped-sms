/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.provider;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 17, 2004 3:34:04 PM
 */
public interface Operator {

    public SmsMethod getMethod();

    public void setMethod(SmsMethod method);

    public String getHost();

    public void setHost(String host);

    public String getPort();

    public void setPort(String port);

    public String getUsername();

    public void setUsername(String username);

    public String getPassword();

    public void setPassword(String password);
}