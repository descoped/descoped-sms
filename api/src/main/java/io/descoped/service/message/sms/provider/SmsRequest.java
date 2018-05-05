/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.provider;

import io.descoped.service.message.sms.common.ConvertException;
import io.descoped.service.message.sms.common.RequestException;
import io.descoped.service.message.sms.model.Messages;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 17, 2004 6:27:28 PM
 */
public interface SmsRequest {
    public Messages getMessages();

    public void setMessages(Messages messages);

    public String getPayload();

    public void setPayload(String payload);

    public void prepare() throws RequestException, ConvertException;
}
