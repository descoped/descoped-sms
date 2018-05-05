/***************************************************************************
 * Copyright 2003-2004 Ove Ranheim         All rights reserved.  *
 * Please look at license.txt in info directory for more license detail.   *
 **************************************************************************/
package io.descoped.service.message.sms.provider;

import io.descoped.service.message.sms.adapter.Adapter;
import io.descoped.service.message.sms.common.CommunicationError;
import io.descoped.service.message.sms.common.ConvertException;
import io.descoped.service.message.sms.common.RequestException;
import io.descoped.service.message.sms.common.ResponseException;
import io.descoped.service.message.sms.model.Messages;

/**
 * @author: Ove Ranheim
 * @email: oranheim@descoped.io
 */
public class SenderImpl implements Sender {

    protected Provider provider;
    protected SmsRequest request;
    protected SmsResponse response;

    public SenderImpl(Provider provider) {
        this.provider = provider;
    }

    /*
     * @see sedna.services.communication.service.provider.Sender#getProvider()
     */
    public Provider getProvider() {
        return provider;
    }

    /*
     * @see sedna.services.communication.service.provider.Sender#clear()
     */
    public void clear() {
        request = null;
        response = null;
    }

    /*
     * @see sedna.services.communication.service.provider.Sender#prepare(sedna.services.communication.service.unit.Messages)
     */
    public void prepare(Messages messages) throws RequestException, ConvertException {
        Adapter adapter = provider.getAdapter();
        request = adapter.getRequest();
        response = adapter.getResponse();
        request.setMessages(messages);
        response.setMessages(messages);
        request.prepare();
    }

    /*
     * @see sedna.services.communication.service.provider.Sender#send()
     */
    public Messages send() throws CommunicationError, ResponseException {
        Messenger messenger = provider.getMessenger();
        messenger.service(request, response);
        return response.getMessages();
    }

}

