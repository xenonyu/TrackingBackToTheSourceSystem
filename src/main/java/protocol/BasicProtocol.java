package protocol;

import java.io.ByteArrayOutputStream;

public abstract class BasicProtocol {
    static final int VER_LEN = 1;
    static final int SECURE_MODE_LEN = 1;
    static final int RESERVED_LEN = 2;
    static final int MESSAGE_SERIAL_LEN = 4;
    static final int MESSAGE_LEN_LEN = 4;
    static final int MESSAGE_TYPE_LEN = 4;
    static final int SENDER_ID_LEN = 16;
    static final int RECEIVER_ID_LEN = 16;
    static final int HEADER_LEN = 48;

    /**
     * ��ȡ��װ�õ�byte[]
     * @return byte[]
     */
    public byte[] getData() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write((byte)(getVersion()));
            baos.write(ProtocolUtil.int2ByteArrays(getSecureMode()), 0, SECURE_MODE_LEN);
            baos.write(ProtocolUtil.int2ByteArrays(getReserved()), 0, RESERVED_LEN);
            baos.write(ProtocolUtil.int2ByteArrays(getMessageSerial()), 0, MESSAGE_SERIAL_LEN);
            baos.write(ProtocolUtil.int2ByteArrays(getMessageLen()), 0, MESSAGE_LEN_LEN);
            baos.write(ProtocolUtil.int2ByteArrays(getType()),0,MESSAGE_TYPE_LEN);
            baos.write(ProtocolUtil.str2ByteArrays(getSenderId()), 0, SENDER_ID_LEN);
            baos.write(ProtocolUtil.str2ByteArrays(getReceiverId()), 0, RECEIVER_ID_LEN);
            baos.write(ProtocolUtil.str2ByteArrays(getMessage(), getMessage().length()), 0, getMessageLen()-HEADER_LEN);
            System.out.println(baos.toByteArray().length);

//            baos.write(ProtocolUtil.int2ByteArrays(getSecureMode()), 0, SECURE_MODE_LEN);
//            baos.write(ProtocolUtil.int2ByteArrays(getReserved()), 0, RESERVED_LEN);
//            baos.write(ProtocolUtil.int2ByteArrays(getMessageSerial()), 0, MESSAGE_SERIAL_LEN);
//            baos.write(ProtocolUtil.int2ByteArrays(getMessageLen()+16), 0, MESSAGE_LEN_LEN);
//            baos.write(ProtocolUtil.int2ByteArrays(getType()),0,MESSAGE_TYPE_LEN);
//            baos.write(ProtocolUtil.str2ByteArrays(getSenderId()), 0, SENDER_ID_LEN);
//            baos.write(ProtocolUtil.str2ByteArrays(getReceiverId()), 0, RECEIVER_ID_LEN);
//            baos.write(ProtocolUtil.str2ByteArrays(getMessage(), getMessage().length()), 0, getMessageLen()-HEADER_LEN);
//            baos.write(ProtocolUtil.str2ByteArrays("11111111"), 0, 16);
            return baos.toByteArray();
        }catch (Exception e){
            System.out.print("Exception occurred\n");
            return null;
        }
    }
    /**
     * ��ȡ�汾��
     * @return
     */
    public abstract int getVersion();

    /**
     * ��ȡ��ȫģʽ
     * @return
     */
    public abstract  int getSecureMode();

    /**
     * ��ȡ��ȫģʽ
     * @return
     */
    public abstract  int getReserved();

    /**
     * ��ȡ��Ϣ���
     * @return
     */
    public abstract  int getMessageSerial();

    /**
     * ��ȡ��Ϣ����
     * @return
     */

    public abstract  int getMessageLen();


    /**
     * ��ȡҵ������
     * @return
     */
    public abstract  int getType();

    /**
     * ��ȡ����ID
     * @return
     */
    public abstract  String getSenderId();

    /**
     * ��ȡ����ID
     * @return
     */
    public abstract  String getReceiverId();

    /**
     * ��ȡ��Ϣ
     * @return
     */
    public abstract  String getMessage();

    /**
     * ��������
     * @param bytes
     */
    public abstract void parseBinary(byte[] bytes);

    public abstract void setMessage(String message);

    public abstract void setMessageLen(int messageLen);

    public abstract void setVersion(int s);

    public abstract void setSecureMode(int secureMode);

    public abstract void setReserved(int reserved);

    public abstract void setMessageSerial(int messageSerial);

    public abstract void setSenderId(String s);

    public abstract void setReceiverId(String s);

}