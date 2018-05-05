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
public interface SocketAdapter {

    /**
     * If failure return false and it'll close the remote connection
     *
     * @param buf received buffer
     * @return success? false will break the connection
     */
    public boolean handle(PrintWriter out, byte[] buf) throws CommunicationError;

}
