/***************************************************************************
 * Copyright 2003-2004 Ove Ranheim         All rights reserved.  *
 * Please look at license.txt in info directory for more license detail.   *
 **************************************************************************/
package io.descoped.service.message.sms.encoder;

import io.descoped.service.message.sms.common.ConvertException;

/**
 * @author: Ove Ranheim
 * @email: oranheim@descoped.io
 */
public interface Formatter {

    /**
     * Formatter service identifier, like Sms operator code for different
     * formats
     */
    public MessageFormat getFormat();

    /**
     * Convert source to wanted message body format
     */
    public Object convert(Object object) throws ConvertException;

}