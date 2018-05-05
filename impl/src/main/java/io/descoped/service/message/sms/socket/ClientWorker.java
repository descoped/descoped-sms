/*
 * Created on 27.jan.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package io.descoped.service.message.sms.socket;

import io.descoped.service.message.sms.common.CommunicationError;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ClientWorker implements Runnable {
    private ServerSocketListener server;
    private Socket client;
    private SocketAdapter adapter;

    public ClientWorker(ServerSocketListener server, Socket client, SocketAdapter adapter) {
        this.server = server;
        this.client = client;
        this.adapter = adapter;
    }

    public void run() {
        System.out.println("New connection from '" + client.getRemoteSocketAddress() + "'");

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            boolean succ = true;
            while (succ) {
                try {
                    String line = in.readLine();
                    // TODO: Can't use bytes for byte streams as they lack support for unicode (use chars)
                    succ = adapter.handle(out, line.getBytes());
                    if (!succ || Thread.currentThread().isInterrupted()) break;
//                    Thread.sleep(50);
                } catch (CommunicationError e) {
                    // TODO: Should use concrete exception classes to handle signals 
                    if ("SHUTDOWN".equals(e.getMessage())) {
                        System.err.println("SHUTDOWN is not properly implemented! The listener doesn't clean up after itslef.");
                        client.close();
                        server.close();
                        throw e;
                    } else {
                        throw new RuntimeException(e);
                    }
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
                }
            }

            in.close();
            out.close();
            client.close();
            System.out.println("Connection closed from '" + client.getRemoteSocketAddress() + "'");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CommunicationError e) {
            if (!"SHUTDOWN".equals(e.getMessage())) throw new RuntimeException(e);
        }
    }
}

