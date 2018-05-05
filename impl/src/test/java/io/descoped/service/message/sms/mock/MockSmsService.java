package io.descoped.service.message.sms.mock;

import io.descoped.service.message.sms.SmsService;
import io.descoped.service.message.sms.common.CommunicationError;
import io.descoped.service.message.sms.common.ConvertException;
import io.descoped.service.message.sms.common.RequestException;
import io.descoped.service.message.sms.common.ResponseException;
import io.descoped.service.message.sms.impl.SmsServiceImpl;
import io.descoped.service.message.sms.model.Message;
import io.descoped.service.message.sms.model.Messages;
import io.descoped.service.message.sms.model.Recipient;
import io.descoped.service.message.sms.provider.Provider;
import io.descoped.service.message.sms.provider.Sender;

/**
 * @author Ove Ranheim (oranheim@yahoo.no)
 * @since Jun 19, 2004 2:04:12 PM
 */
public class MockSmsService extends SmsServiceImpl implements SmsService {

    public MockSmsService() {
        super();
    }

    public Messages createMessages() {
        return super.createMessages();
    }

    public Message createMessage() {
        return super.createMessage();
    }

    public Provider createProdatProvider(String username, String password) {
        return super.createProdatProvider(username, password);
    }

    public Recipient createRecipient(String to) {
        return super.createRecipient(to);
    }

    public Sender createSender(Provider provider) {
        return new MockSender(provider);
    }

    public boolean sendSms(Provider provider, Messages messages) throws CommunicationError, RequestException,
            ResponseException, ConvertException {
        return super.sendSms(provider, messages);
    }

}
