/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.provider.impl;

import io.descoped.service.message.sms.SmsMonitorService;
import io.descoped.service.message.sms.common.CommunicationError;
import io.descoped.service.message.sms.common.ResponseException;
import io.descoped.service.message.sms.impl.SmsMonitorServiceImpl;
import io.descoped.service.message.sms.model.Message;
import io.descoped.service.message.sms.model.MessageStatus;
import io.descoped.service.message.sms.model.Messages;
import io.descoped.service.message.sms.model.Recipient;
import io.descoped.service.message.sms.provider.*;
import io.descoped.service.message.sms.socket.ClientHttpSocket;
import io.descoped.service.message.sms.socket.ClientSocket;

import java.util.Iterator;


/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 17, 2004 8:09:31 PM
 */
public class ProdatMessengerImpl implements Messenger {

    protected Provider provider;
    //protected SmsMonitorService monitor;

    //    @Inject
//    @Exact(SmsMonitorServiceImpl.class)
    SmsMonitorService monitor;

    public ProdatMessengerImpl(Provider provider) {
        this.provider = provider;
        monitor = SmsMonitorServiceImpl.getInstance();
    }

    protected void statusSending(Messages messages) {
        for (Iterator<Message> i = messages.iterator(); i.hasNext(); ) {
            Message m = i.next();
            for (Iterator<Recipient> j = m.getRecipients().iterator(); j.hasNext(); ) {
                Recipient r = j.next();
                r.setStatus(MessageStatus.SENDING);
                monitor.incCountMessages();
            }
        }
    }

    public void service(SmsRequest request, SmsResponse response) throws CommunicationError, ResponseException {
        Operator operator = provider.getOperator();
        SmsMethod method = operator.getMethod();
        String host = operator.getHost();
        int port = Integer.parseInt(operator.getPort());
        String xml = null;

        if (method.equals(SmsMethod.SOCKET_CLIENT)) {
            ClientSocket socket = new ClientSocket(host, port);
            statusSending(request.getMessages());
            xml = socket.sendMessage(request.getPayload());
        } else if (method.equals(SmsMethod.HTTP_CLIENT)) {
            ClientHttpSocket http = new ClientHttpSocket(host, port, operator.getUsername(), operator.getPassword());
            statusSending(request.getMessages());
            xml = http.sendMessage(request.getPayload());
        }

        if (xml == null) throw new CommunicationError("Unknown method to service in prodat messenger");

        response.setResult(xml);
        response.translate();
    }

}
