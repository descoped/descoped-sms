/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.socket;

import io.descoped.service.message.sms.common.CommunicationError;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jul 6, 2004 1:15:03 AM
 */
public class ClientHttpSocket {

    private Logger log = LoggerFactory.getLogger(ClientHttpSocket.class);

    private String host;
    private int port;
    private String username;
    private String password;

    public ClientHttpSocket(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    /**
     * @param payload
     * @return
     */
    public String sendMessage(String payload) throws CommunicationError {
        String response = "";
        try {
            HttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_0);
            client.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");

            URL url = new URL("http", host, port, "/service");
            HttpPost post = new HttpPost(url.toString());
            try {
                ByteArrayEntity messageEntity = new ByteArrayEntity(payload.getBytes());
                messageEntity.setContentType("application/xml");
                messageEntity.setContentEncoding(HTTP.UTF_8);

                post.setEntity(messageEntity);
                HttpResponse httpResponse = client.execute(post);

                HttpEntity responseEntity = httpResponse.getEntity();

                if (responseEntity != null && httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    InputStream responseStream = responseEntity.getContent();
                    int length;
                    byte[] b = new byte[2048];
                    StringBuffer buf = new StringBuffer();
                    while ((length = responseStream.read(b)) != -1) {
                        buf.append(new String(b, 0, length));
                    }
                    response = buf.toString();
                } else {
                    throw new CommunicationError("Unexpected failure: " + httpResponse.getStatusLine().toString());
                }
            } finally {
                client.getConnectionManager().shutdown();
            }
        } catch (IOException e) {
            throw new CommunicationError(e);
        }
        return response;
    }

}
