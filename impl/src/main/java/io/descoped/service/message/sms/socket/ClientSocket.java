/***************************************************************************
 * Copyright 2003-2004 Ove Ranheim         All rights reserved.  *
 * Please look at license.txt in info directory for more license detail.   *
 **************************************************************************/
package io.descoped.service.message.sms.socket;

import io.descoped.service.message.sms.common.CommunicationError;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @author: Ove Ranheim
 * @email: oranheim@descoped.io
 */
public class ClientSocket {

    private static final int SOCKET_TIMEOUT_IN_MILLIS = 10 * 1000;
    private static final int SOCKET_BUFFER_SIZE = 8 * 1024;
    private String ipAddress = "";
    private int port = 0;
    private Charset charset;
    private CharsetEncoder charSetEncoder;
    private CharsetDecoder charSetDecoder;

    public ClientSocket(String host, int port) {
        ipAddress = host;
        this.port = port;

        charset = Charset.forName("ISO-8859-1");
        charSetEncoder = charset.newEncoder();
        charSetDecoder = charset.newDecoder();
    }

    @SuppressWarnings("unused")
    private byte[] charArrayToByteArray(char[] chars, int offset, int len) throws CharacterCodingException {
        CharBuffer charBuf = CharBuffer.wrap(chars, offset, len);
        return charSetEncoder.encode(charBuf).array();
    }

    private char[] byteArrayToCharArray(byte[] bytes, int offset, int len) throws CharacterCodingException {
        ByteBuffer byteBuf = ByteBuffer.wrap(bytes, offset, len);
        return charSetDecoder.decode(byteBuf).array();
    }

    public String sendMessage(String buffer) throws CommunicationError {
        StringBuffer inputBuffer = new StringBuffer();
        PrintWriter out = null;
        BufferedReader in = null;
        Socket writeSocket = new Socket();
        try {
            try {
                SocketAddress remote = new InetSocketAddress(ipAddress, port);
                writeSocket.connect(remote, SOCKET_TIMEOUT_IN_MILLIS);
                OutputStream os = writeSocket.getOutputStream();
                InputStream is = writeSocket.getInputStream();

                out = new PrintWriter(os, true);
                try {
                    in = new BufferedReader(new InputStreamReader(is));
                    try {
                        // Send payload
                        byte[] b_out = buffer.getBytes();
                        int p = 0;
                        int n = b_out.length;
                        while (n > 0) {
                            int m = SOCKET_BUFFER_SIZE;
                            if (n < SOCKET_BUFFER_SIZE) m = n;
                            char[] a = byteArrayToCharArray(b_out, p, m);
                            out.write(a);
                            p = p + m; // next read position
                            n = n - m; // remaning buffer
                        }
                        out.println();

                        // Read resposne
                        char[] b_in = new char[SOCKET_BUFFER_SIZE];
                        while ((n = in.read(b_in)) > 0) {
                            inputBuffer.append(b_in, 0, n);
                        }
                    } finally {
                        in.close();
                    }
                } finally {
                    out.close();
                }
            } catch (UnknownHostException e) {
                throw new CommunicationError("Unknown host: " + ipAddress);
            } finally {
                writeSocket.close();
            }
        } catch (IOException ioe) {
            throw new CommunicationError(ioe);
        }
        return inputBuffer.toString();
    }
}

