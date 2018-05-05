/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.provider;

import io.descoped.service.message.sms.common.CommunicationError;
import io.descoped.service.message.sms.common.ResponseException;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 18, 2004 10:50:16 AM
 */
public interface Messenger {

    public void service(SmsRequest request, SmsResponse response) throws CommunicationError, ResponseException;
}
