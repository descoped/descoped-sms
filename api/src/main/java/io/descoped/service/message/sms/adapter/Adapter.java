/***************************************************************************
 * Copyright 2003-2004 Ove Ranheim         All rights reserved.  *
 * Please look at license.txt in info directory for more license detail.   *
 **************************************************************************/
package io.descoped.service.message.sms.adapter;

import io.descoped.service.message.sms.provider.Provider;
import io.descoped.service.message.sms.provider.SmsRequest;
import io.descoped.service.message.sms.provider.SmsResponse;

/**
 * @author: Ove Ranheim
 * @email: oranheim@descoped.io
 */
public interface Adapter {

    public Provider getProvider();

    public SmsRequest getRequest();

    public SmsResponse getResponse();

}