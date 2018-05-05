/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.provider;

import io.descoped.service.message.sms.common.ResponseException;
import io.descoped.service.message.sms.model.Messages;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 17, 2004 6:29:33 PM
 */
public interface SmsResponse {
    public Messages getMessages();

    public void setMessages(Messages messages);

    public String getResult();

    public void setResult(String payload);

    public void translate() throws ResponseException;
}
