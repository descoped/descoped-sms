package io.descoped.service.message.sms.provider.impl;

import io.descoped.service.message.sms.common.ResponseException;
import io.descoped.service.message.sms.model.LogonStatus;
import io.descoped.service.message.sms.model.Message;
import io.descoped.service.message.sms.model.MessageStatus;
import io.descoped.service.message.sms.model.Recipient;
import io.descoped.service.message.sms.provider.Provider;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.util.Set;

/**
 * @author lukaszk
 * @since 17.12.11 21:18 GMT+1
 */
public class JaxbProdatResponse extends BaseProdatResponse {

    public JaxbProdatResponse(Provider provider) {
        super(provider);
    }

    @Override
    public void translate() throws ResponseException {
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(SmsSession.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            SmsSession smsSession = (SmsSession) unmarshaller.unmarshal(new StringReader(payload));
            SmsStatus smsLogon = smsSession.getSmsLogon();
            messages.setReason(smsSession.getReason());
            messages.setLogonStatus(smsLogon.getLogonStatus());
            if (SmsStatus.FAIL.equals(smsLogon)) {
                messages.errorOccured();
                return;
            }

            boolean hasErrorOccured = Boolean.FALSE;
            for (SmsMessage smsMessage : smsSession.getSmsMessages()) {
                String textId = smsMessage.getId();
                int id = Integer.parseInt(textId);
                Message message = messages.findMessageById(id);
                SmsStatus status = smsMessage.getStatus();
                for (Recipient r : message.getRecipients()) {
                    r.setStatus(status.getMessageStatus());
                    switch (status) {
                        case OK:
                            monitor.incSuccessfulMessages();
                            break;
                        case FAIL:
                            hasErrorOccured = Boolean.TRUE;
                            monitor.incFailedMessages();
                    }
                    r.setError(smsMessage.getInfo());
                }
            }
            if (hasErrorOccured) {
                messages.errorOccured();
            }
        } catch (JAXBException e) {
            log.error("Could not translate service response: " + payload);
            log.error("Error: " + e.getMessage(), e);
        }
    }

    @XmlRootElement(name = "SESSION")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static final class SmsSession {
        @XmlElement(name = "LOGON")
        private SmsStatus smsLogon;
        @XmlElement(name = "REASON")
        private String reason;
        @XmlElementWrapper(name = "MSGLST")
        @XmlElement(name = "MSG")
        private Set<SmsMessage> smsMessages;

        public SmsStatus getSmsLogon() {
            return smsLogon;
        }

        public void setSmsLogon(SmsStatus smsLogon) {
            this.smsLogon = smsLogon;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public Set<SmsMessage> getSmsMessages() {
            return smsMessages;
        }

        public void setSmsMessages(Set<SmsMessage> smsMessages) {
            this.smsMessages = smsMessages;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "MSG")
    public static final class SmsMessage {
        @XmlElement(name = "ID")
        private String id;
        @XmlElement(name = "REF")
        private String ref;
        @XmlElement(name = "STATUS")
        private SmsStatus status;
        @XmlElement(name = "INFO")
        private String info;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRef() {
            return ref;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }

        public SmsStatus getStatus() {
            return status;
        }

        public void setStatus(SmsStatus status) {
            this.status = status;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    @XmlType
    @XmlEnum
    public static enum SmsStatus {
        OK(LogonStatus.SUCCESS, MessageStatus.COMPLETE), FAIL(LogonStatus.FAILED, MessageStatus.FAILURE);

        private final LogonStatus logonStatus;
        private final MessageStatus messageStatus;

        SmsStatus(LogonStatus logonStatus, MessageStatus messageStatus) {
            this.logonStatus = logonStatus;
            this.messageStatus = messageStatus;
        }

        public LogonStatus getLogonStatus() {
            return logonStatus;
        }

        public MessageStatus getMessageStatus() {
            return messageStatus;
        }
    }
}
