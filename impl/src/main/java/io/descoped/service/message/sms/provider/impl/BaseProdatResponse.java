package io.descoped.service.message.sms.provider.impl;

import io.descoped.service.message.sms.SmsMonitorService;
import io.descoped.service.message.sms.impl.SmsMonitorServiceImpl;
import io.descoped.service.message.sms.model.Messages;
import io.descoped.service.message.sms.provider.Provider;
import io.descoped.service.message.sms.provider.SmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lukaszk
 * @since 17.12.11 21:25 GMT+1
 */
public abstract class BaseProdatResponse implements SmsResponse {

    static final Logger log = LoggerFactory.getLogger(BaseProdatResponse.class);

    protected Provider provider;
    protected Messages messages;
    protected String payload;

    protected SmsMonitorService monitor;

    public BaseProdatResponse(Provider provider) {
        this.provider = provider;
        monitor = SmsMonitorServiceImpl.getInstance();
    }

    @Override
    public Messages getMessages() {
        return messages;
    }

    @Override
    public void setMessages(Messages messages) {
        this.messages = messages;
    }

    @Override
    public String getResult() {
        return payload;
    }

    @Override
    public void setResult(String payload) {
        this.payload = payload;
    }
}
