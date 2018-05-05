/***************************************************************************
 * Copyright 2003-2004 Ove Ranheim         All rights reserved.  *
 * Please look at license.txt in info directory for more license detail.   *
 **************************************************************************/
package io.descoped.service.message.sms.util;

/**
 * @author: Ove Ranheim
 * @email: oranheim@descoped.io
 */
public class SortException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    public SortException() {
    }

    public SortException(String message) {
        super(message);
    }

    public SortException(String message, Throwable cause) {
        super(message, cause);
    }

    public SortException(Throwable cause) {
        super(cause);
    }
}