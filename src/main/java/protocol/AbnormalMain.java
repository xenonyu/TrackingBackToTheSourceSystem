package protocol;

import java.awt.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class AbnormalMain {

    public static void main(String args[]){

        String jsonPath = MessageProtocol.class.getClassLoader().getResource("test.json").getPath();
        MessageProtocol mp = new MessageProtocol();
        String message = ProtocolUtil.readJsonFile(jsonPath);
        mp.setVersion(Config.VERSION);
        mp.setMessage(message);

        System.out.println(ProtocolUtil.byte2hex(mp.getData()));

        Socket socket;
        BufferedOutputStream bufferedOutput;
        BufferedInputStream bufferedInput;
        DataOutputStream outputStream;
        DataInputStream inputStream;


//        System.exit(0);

        try {
            socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(Config.ADDRESS, Config.PORT);
            /**
             * begin to connect to the server
             */
            System.out.println("begin to connect to the server: " + Config.ADDRESS + ":" + Config.PORT);
            socket.connect(socketAddress, 1000);
            System.out.println("connect success");
            bufferedOutput = new BufferedOutputStream(socket.getOutputStream());// 包装流
            bufferedInput = new BufferedInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(bufferedOutput);
            inputStream = new DataInputStream(bufferedInput);
            ProtocolUtil.writeOutputStream(mp, outputStream);
            System.out.println("already send.");
            MessageProtocol receiver = (MessageProtocol)ProtocolUtil.readInputStream(inputStream);
            System.out.println("Server message:\n" + receiver.getMessage());
            socket.close();// 关闭套接字
        } catch (IOException e) {
            e.printStackTrace();// IO异常
        }


    }
}
