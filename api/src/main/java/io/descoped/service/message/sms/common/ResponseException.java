/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.common;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 20, 2004 2:43:08 PM
 */
public class ResponseException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public ResponseException() {
        super();
    }

    /**
     * @param message
     */
    public ResponseException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public ResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public ResponseException(Throwable cause) {
        super(cause);
    }
}
