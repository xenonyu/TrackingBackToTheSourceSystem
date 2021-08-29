package main.java.protocol;

import main.java.abnormal_process.AbnormalJson;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class LongClient {
    private static Socket socket;
    public static boolean connetionState = false;
    public static Thread listenThread;
    public static Thread sendThread;
    public static ClientListen listener;
    public static ClientSend sender;
    public static String server_ip;

    public static void putAbnormal(AbnormalJson abnormalJson) {
        sender.abnormalQueue.add(abnormalJson);
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
            server_ip = Config.ADDRESS;
            System.out.println(server_ip);
            socket = new Socket(server_ip, Config.PORT);
            System.out.println("成功连接到：" + socket.getRemoteSocketAddress());
            connetionState = true;
            DataOutputStream dataOutputStream;
            DataInputStream dataInputStream;
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
            listener = new ClientListen(socket, dataInputStream);
            sender = new ClientSend(socket, dataOutputStream);
            listenThread = new Thread(listener);
            sendThread = new Thread(sender);
            listenThread.start();
            sendThread.start();

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
        if (listenThread != null) {
            listenThread.interrupt();
            listenThread = null;
        }
        if (sendThread != null) {
            sendThread.interrupt();
//            System.out.print(sendTask.isInterrupted());
            sendThread = null;
        }
    }
}

class ClientListen implements Runnable {
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
                System.out.println("message from Disposal ystem：");
                System.out.println(receiver.getMessage());

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


class ClientSend implements Runnable {
    private Socket socket;
    private DataOutputStream dataOutputStream;
    public LinkedBlockingQueue<AbnormalJson> abnormalQueue;

    ClientSend(Socket socket, DataOutputStream dataOutputStream) {
        this.socket = socket;
        this.dataOutputStream = dataOutputStream;
        this.abnormalQueue = new LinkedBlockingQueue<>(100);
    }

    @Override
    public void run() {
        try {
            while (LongClient.connetionState ) {
                while (!this.abnormalQueue.isEmpty()) {
                    AbnormalJson abnormalJson = this.abnormalQueue.take();
                    if (abnormalJson.getMessage() != null) {
                        MessageProtocol sender = new MessageProtocol();
                        sender.setMessage(abnormalJson.getMessage());
                        System.out.println("send message: " + abnormalJson.getMessage());
//                        System.out.println("the byte array sent:\n" + ProtocolUtil.byte2hex(sender.getData()));
                        ProtocolUtil.writeOutputStream(sender, dataOutputStream);
                    }
                }
                Thread.sleep(2000);
                System.out.println("refresh queue：");

            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            try {
                System.out.println("发送线程异常。");
                socket.close();
                LongClient.stopTask();
                LongClient.connetionState = false;
                LongClient.reConnect();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }
}
