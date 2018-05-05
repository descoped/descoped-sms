package io.descoped.service.message.sms.mock;

/**
 * @author Ove Ranheim (oranheim@yahoo.no)
 * @since Jun 20, 2004 11:18:17 AM
 */
public class MockResponse {

    public static String RESPONSE_FAIL = "<?xml version=\"1.0\"?>" +
            "<SESSION>" +
            "<LOGON>FAIL</LOGON>" +
            "<REASON>Username/password is incorrect.</REASON>" +
            "</SESSION>";


    public static String RESPONSE = "<?xml version=\"1.0\"?>" +
            "<SESSION>" +
            "<LOGON>OK</LOGON>" +
            "<REASON></REASON>" +
            "<MSGLST>" +
            "<MSG>" +
            "<ID>1</ID>" +
            "<STATUS>OK</STATUS>" +
            "<INFO></INFO>" +
            "</MSG>" +
            "<MSG>" +
            "<ID>2</ID>" +
            "<STATUS>OK</STATUS>" +
            "<INFO></INFO>" +
            "</MSG>" +
            "<MSG>" +
            "<ID>3</ID>" +
            "<STATUS>FAIL</STATUS>" +
            "<INFO>Receiver address (2002) must be at least 9 digits including country-code.</INFO>" +
            "</MSG>" +
            "</MSGLST>" +
            "</SESSION>\n";

}