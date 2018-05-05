/*
 * Created on 27.jan.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package io.descoped.service.message.sms.socket;

import io.descoped.service.message.sms.common.CommunicationError;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author ove
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
final public class ServerSocketListener {

    private int port = -1;
    private static boolean listening;
    private ServerSocket server;
    private SocketAdapter adapter;
    private Thread thread;

    private ServerSocketListener(int port, SocketAdapter adapter) {
        this.port = port;
        this.adapter = adapter;
    }

    private void run() throws CommunicationError {
        listening = true;

        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            listening = false;
            throw new CommunicationError("Could not listen on port " + port);
        }

        while (listening) {
            try {
                ClientWorker worker = new ClientWorker(this, server.accept(), adapter);
                thread = new Thread(worker);
                thread.setPriority(Thread.NORM_PRIORITY);
                thread.start();
            } catch (IOException e) {
                if (listening) {
                    listening = false;
                    throw new CommunicationError("Accept failed: " + port);
                }
            }
        }

        listening = false;
    }

    public void close() throws CommunicationError {
        listening = false;
        try {
            // make a thread list to notify 'sigint' to all connections
            thread.interrupt();
            if (server != null) server.close();
        } catch (IOException e) {
            throw new CommunicationError(e);
        }
        server = null;
    }

    public static ServerSocketListener listen(int port, SocketAdapter adapter) throws CommunicationError {
        ServerSocketListener listener = new ServerSocketListener(port, adapter);
        listener.run();
        return listener;
    }

    /**
     * Demonstrates an "Echo" server.
     * In telnet issue 'exit' to close your connection.
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        System.out.println("Listen..");

        ServerSocketListener server = null;
        SocketAdapter adapter = EchoAdapter.getInstance();
        try {
            server = ServerSocketListener.listen(8001, adapter);
        } finally {
            System.out.println("Halted!");
            if (server.server != null && !server.server.isClosed())
                server.close();
            System.exit(-1);
        }
        server = null;

    }

}