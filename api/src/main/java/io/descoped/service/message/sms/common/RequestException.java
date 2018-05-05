/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.common;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 20, 2004 2:43:30 PM
 */
public class RequestException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public RequestException() {
        super();
    }

    /**
     * @param message
     */
    public RequestException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public RequestException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public RequestException(Throwable cause) {
        super(cause);
    }
}
