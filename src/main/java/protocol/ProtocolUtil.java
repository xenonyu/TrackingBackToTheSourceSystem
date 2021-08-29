package main.java.protocol;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 协议工具类
 * Created by Extends on 2017/2/22.
 */
public class ProtocolUtil {

    private static Map<Integer, String> msgImp = new HashMap<Integer, String>();

    static {
        msgImp.put(0xA1520000, "main.java.protocol.MessageProtocol");
//        msgImp.put(HeartBeatProtocol.TYPE,"main.java.protocol.HeartBeatProtocol");
        msgImp.put(MessageProtocol.messageType, "main.java.protocol.MessageProtocol");
    }

    /**
     * 返回业务类型
     *
     * @param data
     * @return String
     */
    public static String paraseType(byte[] data) {
        return new String(data, 0, BasicProtocol.MESSAGE_TYPE_LEN);
    }

    /**
     * int 转 四位字节
     *
     * @param i
     * @return
     */
    public static byte[] int2ByteArrays(int i) {
        return int2ByteArrays(i, 4);
    }

    public static byte[] int2ByteArrays(int i, int length) {
        byte[] result = new byte[length];
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }

    /**
     * 字节数组转int
     *
     * @param b
     * @return
     */
    public static int byteArrayToInt(byte[] b) {
        int intValue = 0;
        int maxOffset = b.length - 1;
        for (int i = 0; i < b.length; i++) {
            intValue += (b[i] & 0xFF) << (8 * (maxOffset - i));
        }
        return intValue;
    }

    public static int byteArrayToInt(byte b) {
        return b & 0xFF;
    }

    /**
     * 读取包头
     *
     * @param inputStream
     * @return BasicProtocol
     */
    public static BasicProtocol readHead(DataInputStream inputStream) throws Exception {
        byte[] version = new byte[BasicProtocol.VER_LEN];
        int verLen = inputStream.read(version);
        System.out.println("received version: " + byteArrayToInt(version));
        if (verLen != BasicProtocol.VER_LEN) {
//            System.out.println("\nread head fail....\nverlen: " + verLen +
//                    "\nreceived len: " + BasicProtocol.VER_LEN);
            return null;
        }

        byte[] secureMode = new byte[BasicProtocol.SECURE_MODE_LEN];
        int secureModeLen = inputStream.read(secureMode);
        System.out.println("received secure mode: " + byte2hex(secureMode));
        if (secureModeLen != BasicProtocol.SECURE_MODE_LEN) {
            return null;
        }

        byte[] reserved = new byte[BasicProtocol.RESERVED_LEN];
        int reservedLen = inputStream.read(reserved);
        System.out.println("received reserved: " + byte2hex(reserved));
        if (reservedLen != BasicProtocol.RESERVED_LEN) {
            return null;
        }

        byte[] messageSerial = new byte[BasicProtocol.MESSAGE_SERIAL_LEN];
        int messageSerialLen = inputStream.read(messageSerial);
        System.out.println("received messageSerial: " + byte2hex(messageSerial));
        if (messageSerialLen != BasicProtocol.MESSAGE_SERIAL_LEN) {
            return null;
        }

        byte[] messageLen = new byte[BasicProtocol.MESSAGE_LEN_LEN];
        int messageLenLen = inputStream.read(messageLen);
        System.out.println("received messageLen: " + byteArrayToInt(messageLen));
        if (messageLenLen != BasicProtocol.MESSAGE_LEN_LEN) {
            return null;
        }

        byte[] messageType = new byte[BasicProtocol.MESSAGE_TYPE_LEN];
        int messageTypeLen = inputStream.read(messageType);
        System.out.println("received messageType: " + byte2hex(messageType));

        if (messageTypeLen != BasicProtocol.MESSAGE_TYPE_LEN) {
            return null;
        }

        byte[] senderId = new byte[BasicProtocol.SENDER_ID_LEN];
        int senderIdLen = inputStream.read(senderId);
        System.out.println("received senderId: " + new String(senderId));
        if (senderIdLen != BasicProtocol.SENDER_ID_LEN) {
            return null;
        }

        byte[] receiverId = new byte[BasicProtocol.RECEIVER_ID_LEN];
        int receiverIdLen = inputStream.read(receiverId);
        System.out.println("received receiverId: " + new String(receiverId));
        if (receiverIdLen != BasicProtocol.RECEIVER_ID_LEN) {
            return null;
        }
        BasicProtocol basicProtocol = (BasicProtocol) Class.forName(msgImp.get(byteArrayToInt(messageType))).newInstance();
        basicProtocol.setVersion(byteArrayToInt(version));
        basicProtocol.setSecureMode(byteArrayToInt(secureMode));
        basicProtocol.setReceiverId(new String(receiverId));
        basicProtocol.setReserved(byteArrayToInt(reserved));
        basicProtocol.setSenderId(new String(senderId));
        basicProtocol.setMessageLen(byteArrayToInt(messageLen));
        return basicProtocol;

    }


    /**
     * 从输入流中读取数据
     *
     * @param inputStream
     * @return BasicProtocol
     */
    public static BasicProtocol readInputStream(DataInputStream inputStream) throws Exception {

        BasicProtocol basicProtocol = readHead(inputStream);
        byte[] jsonByte = new byte[basicProtocol.getMessageLen() - BasicProtocol.HEADER_LEN];
        int jsonByteLen = inputStream.read(jsonByte);
        if (jsonByteLen != basicProtocol.getMessageLen() - BasicProtocol.HEADER_LEN) return null;
        basicProtocol.parseBinary(jsonByte);

        return basicProtocol;
    }


    /**
     * 向输出流中写入数据
     *
     * @param protocol
     * @param outputStream
     */
    public static boolean writeOutputStream(BasicProtocol protocol, DataOutputStream outputStream) throws IOException {
//            System.out.println("return message Len: " + protocol.getMessageLen() + " true len: " + protocol.getMessage().length());
        byte[] a = protocol.getData();
        outputStream.write(protocol.getData());
        outputStream.flush();
        System.out.println("message contained: " + protocol.getMessageLen() + " bytes are sent.");
        return true;
    }

    public static String byte2hex(byte buffer) {
        String hex = Integer.toHexString(buffer & 0xFF);
        if (hex.length() < 2) {
            hex = "0" + hex;
        }
        return hex;
    }

    public static String byte2hex(byte[] buffer) {
        String h = "";

        for (int i = 0; i < buffer.length; i++) {
            String temp = Integer.toHexString(buffer[i] & 0xFF);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            h = h + " " + temp;
        }

        return h;

    }

    public static String readJsonFile(String filePath) {
        String jsonStr = "";
        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file), "Utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}