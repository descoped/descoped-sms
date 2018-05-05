/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.encoder.impl;

import io.descoped.service.message.sms.encoder.Formatter;
import io.descoped.service.message.sms.encoder.MessageFormat;
import io.descoped.service.message.sms.encoder.Resolver;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 18, 2004 6:12:25 PM
 */
public class ProdatResolverImpl implements Resolver {

    private static ProdatResolverImpl me;

    /*
     * @see sedna.services.communication.service.encoder.Resolver#getFormatter(sedna.services.communication.service.encoder.MessageFormat)
     */
    public Formatter getFormatter(MessageFormat format) {
        Formatter formatter = null;
        if (format.equals(MessageFormat.PLAIN_TEXT))
            formatter = TextFormatter.getInstance();
        else if (format.equals(MessageFormat.RINGTONE))
            formatter = RingtoneFormatter.getInstance();
        else if (format.equals(MessageFormat.OPERATOR_LOGO))
            formatter = OperatorLogoFormatter.getInstance();
        else if (format.equals(MessageFormat.CALLER_GROUP_GRAPHIC))
            formatter = CallerGroupGraphicFormatter.getInstance();
        else if (format.equals(MessageFormat.PICTURE))
            formatter = PictureFormatter.getInstance();
        else if (format.equals(MessageFormat.VCARD))
            formatter = VCardFormatter.getInstance();
        else if (format.equals(MessageFormat.VCALENDAR))
            formatter = VCalendarFormatter.getInstance();
        else if (format.equals(MessageFormat.RAW_BINARY_UDH))
            formatter = RawBinaryUDHFormatter.getInstance();
        else if (format.equals(MessageFormat.UNICODE_TEXT)) formatter = UnicodeFormatter.getInstance();
        return formatter;
    }

    /*
     * @see sedna.services.communication.service.encoder.Resolver#getOperationCode(sedna.services.communication.service.encoder.MessageFormat)
     */
    public Object getOperationCode(MessageFormat format) {
        Object opcode = null;
        if (format.equals(MessageFormat.PLAIN_TEXT))
            opcode = new Integer(1);
        else if (format.equals(MessageFormat.RINGTONE))
            opcode = new Integer(2);
        else if (format.equals(MessageFormat.OPERATOR_LOGO))
            opcode = new Integer(3);
        else if (format.equals(MessageFormat.CALLER_GROUP_GRAPHIC))
            opcode = new Integer(4);
        else if (format.equals(MessageFormat.PICTURE))
            opcode = new Integer(5);
        else if (format.equals(MessageFormat.VCARD))
            opcode = new Integer(6);
        else if (format.equals(MessageFormat.VCALENDAR))
            opcode = new Integer(7);
        else if (format.equals(MessageFormat.RAW_BINARY_UDH))
            opcode = new Integer(8);
        else if (format.equals(MessageFormat.UNICODE_TEXT)) opcode = new Integer(9);
        return opcode;
    }

    public static ProdatResolverImpl getInstance() {
        if (me == null) me = new ProdatResolverImpl();
        return me;
    }

}