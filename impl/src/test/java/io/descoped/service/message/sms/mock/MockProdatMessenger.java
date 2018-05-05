package io.descoped.service.message.sms.mock;

import io.descoped.service.message.sms.common.ResponseException;
import io.descoped.service.message.sms.provider.Messenger;
import io.descoped.service.message.sms.provider.Provider;
import io.descoped.service.message.sms.provider.SmsRequest;
import io.descoped.service.message.sms.provider.SmsResponse;
import io.descoped.service.message.sms.provider.impl.ProdatMessengerImpl;

/**
 * @author Ove Ranheim (oranheim@yahoo.no)
 * @since Jun 17, 2004 8:09:31 PM
 */
public class MockProdatMessenger extends ProdatMessengerImpl implements Messenger {

    public MockProdatMessenger(Provider provider) {
        super(provider);
    }

    public void service(SmsRequest request, SmsResponse response) throws ResponseException {
        String ip = provider.getOperator().getHost();
        int port = Integer.parseInt(provider.getOperator().getPort());
        String payload = request.getPayload();
        statusSending(request.getMessages());
        response.setResult(MockResponse.RESPONSE);
        response.translate();

    }

}
