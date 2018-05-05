/***************************************************************************
 * Copyright 2003-2004 Ove Ranheim         All rights reserved.  *
 * Please look at license.txt in info directory for more license detail.   *
 **************************************************************************/
package io.descoped.service.message.sms.encoder.impl;

import io.descoped.service.message.sms.common.ConvertException;
import io.descoped.service.message.sms.encoder.Formatter;
import io.descoped.service.message.sms.encoder.MessageFormat;
import io.descoped.service.message.sms.util.SmsUtil;

/**
 * @author: Ove Ranheim
 * @email: oranheim@descoped.io
 */
public class UnicodeFormatter implements Formatter {

    private static Formatter me;

    public UnicodeFormatter() {
    }

    public MessageFormat getFormat() {
        return MessageFormat.UNICODE_TEXT;
    }

    public Object convert(Object object) throws ConvertException {
        return SmsUtil.encodeUnicodeHexEncoded(object);
    }

    public static Formatter getInstance() {
        if (me == null) me = new UnicodeFormatter();
        return me;
    }

}

