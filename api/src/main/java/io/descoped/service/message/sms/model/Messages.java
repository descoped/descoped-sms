/**
 * Copyright 2003-2004 Ove Ranheim All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/
package io.descoped.service.message.sms.model;

import java.util.Iterator;

/**
 * @author Ove Ranheim (oranheim@descoped.io)
 * @since Jun 17, 2004 4:10:47 PM
 */
public interface Messages {

    public LogonStatus getLogonStatus();

    public void setLogonStatus(LogonStatus logonStatus);

    public String getEncoding();

    public void setEncoding(String encoding);

    public String getReason();

    public void setReason(String reason);

    public boolean hasErrorOccured();

    public void errorOccured();

    public Iterator<Message> iterator();

    public Message getMessage(int index);

    public Message addMessage();

    public Message addMessage(Message message);

    public void removeMessage(Message message);

    public Message findMessageById(Integer id);

    public int count();

    public void clear();

}