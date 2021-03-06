/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.provider.impl;

import io.descoped.service.message.sms.provider.Operator;
import io.descoped.service.message.sms.provider.SmsMethod;


/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 17, 2004 9:10:35 PM
 */
public class ProdatOperatorImpl implements Operator {

    private SmsMethod method = SmsMethod.HTTP_CLIENT;
    private String affiliateProgram;
    private String socket_host = "217.149.126.50";
    private String http_host = "217.149.126.53";
    private String socket_port = "1111";
    private String http_port = "80";
    private String username;
    private String password;

    public ProdatOperatorImpl() {
    }


    /*
     * @see io.descoped.service.message.sms.provider.Operator#getMethod()
     */
    public SmsMethod getMethod() {
        return method;
    }


    /*
     * @see io.descoped.service.message.sms.provider.Operator#setMethod(io.descoped.service.message.sms.provider.SmsMethod)
     */
    public void setMethod(SmsMethod method) {
        this.method = method;
    }

    @Override
    public String getAffiliateProgram() {
        return affiliateProgram;
    }

    @Override
    public void setAffiliateProgram(String affiliateProgram) {
        this.affiliateProgram = affiliateProgram;
    }

    /*
     * @see io.descoped.service.message.sms.provider.Operator#getIP()
     */
    public String getHost() {
        return (method.equals(SmsMethod.SOCKET_CLIENT) ? socket_host : http_host);
    }

    /*
     * @see io.descoped.service.message.sms.provider.Operator#setIP(java.lang.String)
     */
    public void setHost(String host) {
        if (method.equals(SmsMethod.SOCKET_CLIENT)) {
            socket_host = host;
        } else if (method.equals(SmsMethod.HTTP_CLIENT)) {
            http_host = host;
        }
    }

    /*
     * @see io.descoped.service.message.sms.provider.Operator#getPort()
     */
    public String getPort() {
        return (method.equals(SmsMethod.SOCKET_CLIENT) ? socket_port : http_port);
    }

    /*
     * @see io.descoped.service.message.sms.provider.Operator#setPort(java.lang.String)
     */
    public void setPort(String port) {
        if (method.equals(SmsMethod.SOCKET_CLIENT)) {
            socket_port = port;
        } else if (method.equals(SmsMethod.HTTP_CLIENT)) {
            http_port = port;
        }
    }

    /*
     * @see io.descoped.service.message.sms.provider.Operator#getUsername()
     */
    public String getUsername() {
        return username;
    }

    /*
     * @see io.descoped.service.message.sms.provider.Operator#setUsername(java.lang.String)
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /*
     * @see io.descoped.service.message.sms.provider.Operator#getPassword()
     */
    public String getPassword() {
        return password;
    }

    /*
     * @see io.descoped.service.message.sms.provider.Operator#setPassword(java.lang.String)
     */
    public void setPassword(String password) {
        this.password = password;
    }

}


