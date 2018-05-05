/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.impl;

import io.descoped.service.message.sms.SmsMonitorService;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 20, 2004 5:54:56 PM
 */
public class SmsMonitorServiceImpl implements SmsMonitorService {

    private static SmsMonitorServiceImpl me;

    private static Integer countMessages;
    private static Integer successfullMessages;
    private static Integer failedMessages;

    public SmsMonitorServiceImpl() {
        countMessages = new Integer(0);
        successfullMessages = new Integer(0);
        failedMessages = new Integer(0);
    }

    public Integer getCountMessages() {
        return countMessages;
    }

    public void incCountMessages() {
        synchronized (countMessages) {
            int counter = countMessages.intValue();
            counter++;
            countMessages = new Integer(counter);
        }
    }

    public Integer getFailedMessages() {
        return failedMessages;
    }

    public void incFailedMessages() {
        synchronized (failedMessages) {
            int counter = failedMessages.intValue();
            counter++;
            failedMessages = new Integer(counter);
        }
    }

    public Integer getSuccessfullMessages() {
        return successfullMessages;
    }

    public void incSuccessfulMessages() {
        synchronized (successfullMessages) {
            int counter = successfullMessages.intValue();
            counter++;
            successfullMessages = new Integer(counter);
        }
    }

    public Integer getErroneousMessage() {
        synchronized (countMessages) {
            int counter = countMessages.intValue();
            int success = successfullMessages.intValue();
            int failed = failedMessages.intValue();
            return new Integer(counter - success - failed);
        }
    }

    static final public SmsMonitorService getInstance() {
        if (me == null) {
            me = new SmsMonitorServiceImpl();
        }
        return me;
    }
}
