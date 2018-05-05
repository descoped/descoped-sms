/*
 * Created on 27.jan.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package io.descoped.service.message.sms.socket;

import io.descoped.service.message.sms.common.CommunicationError;

import java.io.PrintWriter;

/**
 * @author ove
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EchoAdapter implements SocketAdapter {

    /*
     * This class must be implemented without accessor fields in class scope in order to be thread-safe
     * The adapter is invoked in context of a thread
     */

    private static SocketAdapter me;

    public EchoAdapter() {
    }

    public boolean handle(PrintWriter out, byte[] buf) throws CommunicationError {
        String chunk = new String(buf);

        if ("exit".equalsIgnoreCase(chunk)) return false;
        if ("shutdown".equalsIgnoreCase(chunk)) throw new CommunicationError("SHUTDOWN");

        // Print to stdout
        System.out.println(chunk);

        // Print to remote client
        out.println(chunk);

        return true;
    }

    public static SocketAdapter getInstance() {
        if (me == null) me = new EchoAdapter();
        return me;
    }

}
