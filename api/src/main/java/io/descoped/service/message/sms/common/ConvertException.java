/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.common;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 20, 2004 4:04:52 PM
 */
public class ConvertException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public ConvertException() {
        super();
    }

    /**
     * @param message
     */
    public ConvertException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public ConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public ConvertException(Throwable cause) {
        super(cause);
    }
}
