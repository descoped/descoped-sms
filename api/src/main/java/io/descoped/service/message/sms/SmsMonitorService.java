/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 20, 2004 5:55:44 PM
 */
public interface SmsMonitorService {

    /**
     * @return
     */
    public Integer getCountMessages();

    /**
     *
     */
    public void incCountMessages();

    /**
     * @return
     */
    public Integer getFailedMessages();

    /**
     *
     */
    public void incFailedMessages();

    /**
     * @return
     */
    public Integer getSuccessfullMessages();

    /**
     *
     */
    public void incSuccessfulMessages();

    /**
     * @return
     */
    public Integer getErroneousMessage();

}
