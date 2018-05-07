/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms;

import io.descoped.service.message.sms.common.CommunicationError;
import io.descoped.service.message.sms.common.ConvertException;
import io.descoped.service.message.sms.common.RequestException;
import io.descoped.service.message.sms.common.ResponseException;
import io.descoped.service.message.sms.model.Message;
import io.descoped.service.message.sms.model.Messages;
import io.descoped.service.message.sms.model.Recipient;
import io.descoped.service.message.sms.provider.Provider;
import io.descoped.service.message.sms.provider.Sender;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 17, 2004 7:51:38 PM
 */
public interface SmsService {

    public Provider createProdatProvider(String username, String password);

    public Provider createProdatProvider(String affiliateProgram, String username, String password);

    public Sender createSender(Provider provider);

    public Messages createMessages();

    public Message createMessage();

    public Recipient createRecipient(String to);

    public boolean sendSms(Provider provider, Messages messages) throws CommunicationError, RequestException, ResponseException, ConvertException;

}

