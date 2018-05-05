/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.provider;

import io.descoped.service.message.sms.adapter.Adapter;
import io.descoped.service.message.sms.encoder.Resolver;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 17, 2004 3:36:09 PM
 */
public interface Provider {

    public Operator getOperator();

    public void setOperator(Operator operator);

    public Adapter getAdapter();

    public void setAdapter(Adapter adapter);

    public Messenger getMessenger();

    public void setMessenger(Messenger messenger);

    public Resolver getResolver();

}