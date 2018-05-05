/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.provider;

import io.descoped.service.message.sms.common.CommunicationError;
import io.descoped.service.message.sms.common.ConvertException;
import io.descoped.service.message.sms.common.RequestException;
import io.descoped.service.message.sms.common.ResponseException;
import io.descoped.service.message.sms.model.Messages;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 17, 2004 7:58:02 PM
 */
public interface Sender {

    public Provider getProvider();

    public void prepare(Messages messages) throws RequestException, ConvertException;

    public Messages send() throws CommunicationError, ResponseException;

    public void clear();

}
