package io.descoped.service.message.sms.mock;

import io.descoped.service.message.sms.common.CommunicationError;
import io.descoped.service.message.sms.common.ResponseException;
import io.descoped.service.message.sms.model.Messages;
import io.descoped.service.message.sms.provider.Messenger;
import io.descoped.service.message.sms.provider.Provider;
import io.descoped.service.message.sms.provider.Sender;
import io.descoped.service.message.sms.provider.SenderImpl;

/**
 * @author Ove Ranheim (oranheim@yahoo.no)
 * @since Jun 20, 2004 12:05:20 PM
 */
public class MockSender extends SenderImpl implements Sender {

    public MockSender(Provider provider) {
        super(provider);
    }

    /*
     * @see io.descoped.service.message.sms.provider.Sender#send()
     */
    public Messages send() throws CommunicationError, ResponseException {
        Messenger messenger = new MockProdatMessenger(provider);
        messenger.service(request, response);
        return response.getMessages();
    }
}