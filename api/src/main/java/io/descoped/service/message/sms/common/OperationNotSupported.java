/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.common;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 18, 2004 6:03:18 PM
 */
public class OperationNotSupported extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public OperationNotSupported() {
        super();
    }

    /**
     * @param message
     */
    public OperationNotSupported(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public OperationNotSupported(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public OperationNotSupported(Throwable cause) {
        super(cause);
    }
}
