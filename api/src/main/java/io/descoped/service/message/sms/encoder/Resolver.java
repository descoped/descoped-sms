/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.encoder;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 18, 2004 6:12:46 PM
 */
public interface Resolver {
    public Formatter getFormatter(MessageFormat format);

    public Object getOperationCode(MessageFormat format);
}
