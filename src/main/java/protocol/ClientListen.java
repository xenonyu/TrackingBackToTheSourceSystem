package main.java.protocol;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientListen implements Runnable {
    private Socket socket;
    private DataInputStream dataInputStream;

    ClientListen(Socket socket, DataInputStream dataInputStream) {
        this.socket = socket;
        this.dataInputStream = dataInputStream;
    }

    public void run() {
        try {
            while (LongClient.connetionState) {
                MessageProtocol receiver = (MessageProtocol) ProtocolUtil.readInputStream(dataInputStream);
                System.out.println("回复消息：");
                System.out.println(receiver.getMessage());
                LongClient.synchronize[0]--;
                synchronized (LongClient.synchronize) {
                    if (LongClient.synchronize[0] == 0) {
                        System.out.println("free send thread....");
                        LongClient.synchronize.notifyAll();
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                System.out.println("接收线程异常。");
                socket.close();
                LongClient.stopTask();
                LongClient.connetionState = false;
                LongClient.reConnect();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
