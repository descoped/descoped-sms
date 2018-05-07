/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.provider.impl;

import io.descoped.service.message.sms.adapter.Adapter;
import io.descoped.service.message.sms.adapter.impl.ProdatAdapterImpl;
import io.descoped.service.message.sms.encoder.Resolver;
import io.descoped.service.message.sms.encoder.impl.ProdatResolverImpl;
import io.descoped.service.message.sms.provider.Messenger;
import io.descoped.service.message.sms.provider.Operator;
import io.descoped.service.message.sms.provider.Provider;


/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 17, 2004 9:08:23 PM
 */
public class ProdatProviderImpl implements Provider {

    private Operator operator;
    private Adapter adapter;
    private Messenger messenger;
    private Resolver resolver;

    public ProdatProviderImpl() {
        operator = new ProdatOperatorImpl();
        adapter = new ProdatAdapterImpl(this);
        messenger = new ProdatMessengerImpl(this);
        resolver = ProdatResolverImpl.getInstance();
    }

    public ProdatProviderImpl(String username, String password) {
        operator = new ProdatOperatorImpl();
        adapter = new ProdatAdapterImpl(this);
        messenger = new ProdatMessengerImpl(this);
        resolver = ProdatResolverImpl.getInstance();
        operator.setUsername(username);
        operator.setPassword(password);
    }

    public ProdatProviderImpl(String affiliateProgram, String username, String password) {
        operator = new ProdatOperatorImpl();
        operator.setAffiliateProgram(affiliateProgram);
        adapter = new ProdatAdapterImpl(this);
        messenger = new ProdatMessengerImpl(this);
        resolver = ProdatResolverImpl.getInstance();
        operator.setUsername(username);
        operator.setPassword(password);
    }

    /*
     * @see io.descoped.service.message.sms.provider.Provider#getOperator()
     */
    public Operator getOperator() {
        return operator;
    }

    /*
     * @see io.descoped.service.message.sms.provider.Provider#setOperator(io.descoped.service.message.sms.provider.Operator)
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    /*
     * @see io.descoped.service.message.sms.provider.Provider#getAdapter()
     */
    public Adapter getAdapter() {
        return adapter;
    }

    /*
     * @see io.descoped.service.message.sms.provider.Provider#setAdapter(io.descoped.service.message.sms.adapter.Adapter)
     */
    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
    }

    /*
     * @see io.descoped.service.message.sms.provider.Provider#getMessenger()
     */
    public Messenger getMessenger() {
        return messenger;
    }

    /*
     * @see io.descoped.service.message.sms.provider.Provider#setMessenger(io.descoped.service.message.sms.provider.Messenger)
     */
    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    /*
     * @see io.descoped.service.message.sms.provider.Provider#getResolver()
     */
    public Resolver getResolver() {
        return resolver;
    }


}
