package main.java.protocol;

import java.io.*;

/**
 * 普通文字消息
 * Created by Extends on 2017/2/22.
 */
public class MessageProtocol extends BasicProtocol {

    private String jsonStr;
    private int version = 1;
    private int secureMode = 0x0000;
    private int reserved = 0;
    private int messageSerial = 0;
    private int messageLen = 0;
    public static int messageType = 0xA1540000;
    private String senderId = "1000000000000002";
    private String receiverId = "1000000000000001";

    public void setMessage(String message) throws java.lang.NullPointerException {
        this.jsonStr = message;
        setMessageLen(message.length() + BasicProtocol.HEADER_LEN);
    }

    public void setMessageLen(int messageLen) {
        this.messageLen = messageLen;
    }

    public void setVersion(int s) {
        this.version = s;
    }

    public void setSecureMode(int secureMode) {
        this.secureMode = secureMode;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    public void setMessageSerial(int messageSerial) {
        this.messageSerial = messageSerial;
    }

    public void setSenderId(String s) {
        this.senderId = s;
    }

    public void setReceiverId(String s) {
        this.receiverId = s;
    }

    @Override
    public int getVersion() {
        return this.version;
    }

    @Override
    public int getSecureMode() {
        return this.secureMode;
    }

    @Override
    public int getReserved() {
        return this.reserved;
    }

    @Override
    public int getMessageSerial() {
        return this.messageSerial;
    }

    @Override
    public int getMessageLen() {
        return this.messageLen;
    }

    @Override
    public int getType() {
        return messageType;
    }

    @Override
    public String getSenderId() {
        return this.senderId;
    }

    @Override
    public String getReceiverId() {
        return this.receiverId;
    }

    @Override
    public String getMessage() {
        return jsonStr;
    }

    @Override
    public void parseBinary(byte[] bytes) {
        setMessage(new String(bytes));
    }


}