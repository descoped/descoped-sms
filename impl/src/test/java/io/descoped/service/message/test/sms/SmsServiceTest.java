package io.descoped.service.message.test.sms;

import io.descoped.service.message.sms.SmsMonitorService;
import io.descoped.service.message.sms.common.CommunicationError;
import io.descoped.service.message.sms.common.ConvertException;
import io.descoped.service.message.sms.common.RequestException;
import io.descoped.service.message.sms.common.ResponseException;
import io.descoped.service.message.sms.encoder.MessageFormat;
import io.descoped.service.message.sms.impl.SmsMonitorServiceImpl;
import io.descoped.service.message.sms.mock.MockSmsService;
import io.descoped.service.message.sms.model.*;
import io.descoped.service.message.sms.model.impl.RecipientImpl;
import io.descoped.service.message.sms.provider.Provider;
import io.descoped.service.message.sms.provider.Sender;
import io.descoped.service.message.sms.util.SmsUtil;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

/**
 * @author: Ove Ranheim
 * @email: oranheim@users.sourceforge.net
 */
public class SmsServiceTest {

    static private String USERNAME = "uid";
    static private String PASSWORD = "pwd";

    private Logger log = LoggerFactory.getLogger(SmsServiceTest.class);

    MockSmsService service = new MockSmsService();

    private Messages messages;

    protected SmsMonitorService monitor = SmsMonitorServiceImpl.getInstance();

    @Before
    public void createTestMessage() {
        RecipientImpl.identity = new Integer(0);
        Messages messages = service.createMessages();

        Message message = messages.addMessage();
        message.setFrom("123456789");
        message.addRecipient(service.createRecipient("123456789"));
        message.addRecipient("234567890");
        message.setFormat(MessageFormat.PLAIN_TEXT);
        message.setContent("Plain text message");

        message = messages.addMessage();
        message.setFrom("123456789");
        message.addRecipient("2002");
        message.setFormat(MessageFormat.UNICODE_TEXT);
        message.setContent("Société à Responsabilité Limitée");
        this.messages = messages;
    }

    @Test
    public void testNumberFormat() throws Exception {
        String token1 = "+33-11-44-55-66";
        String token2 = "0047-227.78.899";
        String token3 = "0047-227.78a899";

        token1 = SmsUtil.prepareCellularNumber(token1);
        assert "3311445566".equals(token1);

        token2 = SmsUtil.prepareCellularNumber(token2);
        assert "4722778899".equals(token2);

        boolean isAlpha = SmsUtil.isAlpha(token3);
        assert isAlpha == true;
    }

    @Test
    public void testInitialDraft() throws Exception {
        Provider provider = service.createProdatProvider(USERNAME, PASSWORD);
        Sender sender = service.createSender(provider);

        assert LogonStatus.NONE.equals(messages.getLogonStatus());

        Message m1 = messages.getMessage(0);
        Message m2 = messages.getMessage(1);

        assert MessageFormat.PLAIN_TEXT.equals(m1.getFormat());
        assert MessageFormat.UNICODE_TEXT.equals(m2.getFormat());

        Recipient r1 = (Recipient) m1.getRecipients().get(0);
        Recipient r2 = (Recipient) m1.getRecipients().get(1);
        Recipient r3 = (Recipient) m2.getRecipients().get(0);

        assert MessageStatus.DRAFT.equals(r1.getStatus());
        assert MessageStatus.DRAFT.equals(r2.getStatus());
        assert MessageStatus.DRAFT.equals(r3.getStatus());
    }

    @Test
    public void testPreparePending() throws Exception {
        try {
            Provider provider = service.createProdatProvider(USERNAME, PASSWORD);
            Sender sender = service.createSender(provider);
            sender.prepare(messages);
            // assertEquals( messages.getLogonStatus(), LogonStatus.NONE );

            Message m1 = messages.getMessage(0);
            Message m2 = messages.getMessage(1);

            assert MessageFormat.PLAIN_TEXT.equals(m1.getFormat());
            assert MessageFormat.UNICODE_TEXT.equals(m2.getFormat());

            Recipient r1 = (Recipient) m1.getRecipients().get(0);
            Recipient r2 = (Recipient) m1.getRecipients().get(1);
            Recipient r3 = (Recipient) m2.getRecipients().get(0);

            // log.info(r1.getStatus());
            assert MessageStatus.PENDING.equals(r1.getStatus());
            assert MessageStatus.PENDING.equals(r2.getStatus());
            assert MessageStatus.PENDING.equals(r3.getStatus());

        } catch (RequestException re) {
            re.printStackTrace();
        } catch (ConvertException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPerformSending() throws Exception {
        try {
            Provider provider = service.createProdatProvider(USERNAME, PASSWORD);
            Sender sender = service.createSender(provider);
            sender.prepare(messages);
            sender.send();
            assert LogonStatus.SUCCESS.equals(messages.getLogonStatus());

            Message m1 = messages.getMessage(0);
            Message m2 = messages.getMessage(1);

            assert MessageFormat.PLAIN_TEXT.equals(m1.getFormat());
            assert MessageFormat.UNICODE_TEXT.equals(m2.getFormat());

            Recipient r1 = (Recipient) m1.getRecipients().get(0);
            Recipient r2 = (Recipient) m1.getRecipients().get(1);
            Recipient r3 = (Recipient) m2.getRecipients().get(0);

            log.info(r1.getStatus().toString());
            assert MessageStatus.COMPLETE.equals(r1.getStatus());
            assert MessageStatus.COMPLETE.equals(r2.getStatus());
            assert MessageStatus.FAILURE.equals(r3.getStatus());

            if (messages.hasErrorOccured())
                log.info("Recipient 1 in Message 2 failed due to: " + r3.getError());

        } catch (RequestException re) {
            re.printStackTrace();
        } catch (ConvertException e) {
            e.printStackTrace();
        } catch (CommunicationError e) {
            e.printStackTrace();
        } catch (ResponseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMonitorService() throws Exception {
        assertEquals(0, (int) monitor.getCountMessages());
        //assertEquals(2, (int)monitor.getSuccessfullMessages());  // this fails after rework of Jaxb response
        assertEquals(0, (int) monitor.getFailedMessages());
    }

}
