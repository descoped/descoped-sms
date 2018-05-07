/***************************************************************************
 * Copyright 2003-2004 Ove Ranheim         All rights reserved.  *
 * Please look at license.txt in info directory for more license detail.   *
 **************************************************************************/
package io.descoped.service.message.sms.adapter.impl;

import io.descoped.service.message.sms.adapter.Adapter;
import io.descoped.service.message.sms.provider.Provider;
import io.descoped.service.message.sms.provider.SmsRequest;
import io.descoped.service.message.sms.provider.SmsResponse;
import io.descoped.service.message.sms.provider.impl.JaxbProdatResponse;
import io.descoped.service.message.sms.provider.impl.ProdatRequestImpl;


/**
 * @author: Ove Ranheim
 * @email: oranheim@descoped.io
 */
public class ProdatAdapterImpl implements Adapter {

    private Provider provider;
    private SmsRequest request;
    private SmsResponse response;

    public ProdatAdapterImpl(Provider provider) {
        this.provider = provider;
        request = new ProdatRequestImpl(provider);
        response = new JaxbProdatResponse(provider);
    }

    public Provider getProvider() {
        return provider;
    }

    /*
     * @see io.descoped.service.message.sms.adapter.Adapter#getRequest()
     */
    public SmsRequest getRequest() {
        return request;
    }

    /*
     * @see io.descoped.service.message.sms.adapter.Adapter#getResponse()
     */
    public SmsResponse getResponse() {
        return response;
    }

}