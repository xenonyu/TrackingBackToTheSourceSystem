package main.java.protocol;

import main.java.abnormal_process.AbnormalJson;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class LongClient {
    private static Socket socket;
    public static boolean connetionState = false;
    public static Thread listen;
    public static Thread send;
    public static String server_ip;
    public static LinkedBlockingQueue<AbnormalJson> abnormalQueue = new LinkedBlockingQueue<>(100);
    public static Integer[] synchronize = new Integer[2];

    public static void putAbnormal(AbnormalJson abnormalJson) {
    }

    public static void main(String args[]) {
        while (!connetionState) {
            try {
                connect();
//                showThread();
                Thread.sleep(3000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static void connect() {
        try {
            server_ip = Config.LOCAL_ADDRESS;
            System.out.println(server_ip);
            socket = new Socket(server_ip, Config.PORT);
            System.out.println("成功连接到：" + socket.getRemoteSocketAddress());
            connetionState = true;
            DataOutputStream dataOutputStream;
            DataInputStream dataInputStream;
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
            listen = new Thread(new ClientListen(socket, dataInputStream));
            send = new Thread(new ClientSend(socket, dataOutputStream, abnormalQueue));
            listen.start();
            send.start();

//            new Thread(new ClientHeart(socket, dataOutputStream)).start();
        } catch (IOException e) {
            e.printStackTrace();
            connetionState = false;
        }
    }

    public static void reConnect() {
        while (!connetionState) {
            System.out.println("Trying to reconnect.........");
            connect();
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void stopTask() {
        System.out.println("关闭发送和接收进程；");
        if (listen != null) {
            listen.interrupt();
            listen = null;
        }
        if (send != null) {
            send.interrupt();
//            System.out.print(sendTask.isInterrupted());
            send = null;
        }
    }
}
