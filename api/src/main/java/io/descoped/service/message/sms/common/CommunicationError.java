/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.common;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 20, 2004 2:42:32 PM
 */
public class CommunicationError extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public CommunicationError() {
        super();
    }

    /**
     * @param message
     */
    public CommunicationError(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public CommunicationError(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public CommunicationError(Throwable cause) {
        super(cause);
    }
}
