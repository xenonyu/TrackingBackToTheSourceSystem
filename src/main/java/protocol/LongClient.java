package protocol;

import abnormal_process.AbnormalJson;
import com.alibaba.fastjson.JSONObject;

import java.awt.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class LongClient {
    private static Socket socket;
    public static boolean connetionState = false;
    public static Thread listenTask;
    public static Thread sendTask;
    public static void main(String args[]){
        while(!connetionState){
            try {
                connect();
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static void connect() {
        try {
            socket = new Socket(Config.LOCAL_ADDRESS, Config.PORT);
            System.out.println("成功连接到：" + socket.getRemoteSocketAddress());
            connetionState = true;
            DataOutputStream dataOutputStream;
            DataInputStream dataInputStream;
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
            listenTask = new Thread(new ClientListen(socket, dataInputStream));
            sendTask = new Thread(new ClientSend(socket, dataOutputStream));
            listenTask.start();
            sendTask.start();

//            new Thread(new ClientHeart(socket, dataOutputStream)).start();
        } catch (IOException e) {
            e.printStackTrace();
            connetionState = false;
        }
    }

    public static void reConnect(){
        while(!connetionState){
            System.out.println("Trying to reconnect.........");
            connect();
            try{
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public static void stopTask(){
        System.out.println("关闭发送和接收进程；");
        if(listenTask != null){
            listenTask.interrupt();
            listenTask = null;
        }
        if(sendTask != null){
            sendTask.interrupt();
//            System.out.print(sendTask.isInterrupted());
            sendTask = null;
        }
    }
}


class ClientListen implements Runnable{
    private Socket socket;
    private DataInputStream dataInputStream;
    ClientListen(Socket socket, DataInputStream dataInputStream){
        this.socket = socket;
        this.dataInputStream = dataInputStream;
    }

    public void run() {
        try {
            while(LongClient.connetionState){
                MessageProtocol receiver = (MessageProtocol)ProtocolUtil.readInputStream(dataInputStream);
                System.out.println("回复消息：");
                System.out.println(receiver.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
            try{
                System.out.println("接收线程异常。");
                socket.close();
                LongClient.connetionState = false;
                LongClient.reConnect();
            } catch (Exception ee){
                ee.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LongClient.stopTask();
        }
    }
}

class ClientSend implements Runnable{
    private Socket socket;
    private DataOutputStream dataOutputStream;
    ClientSend(Socket socket, DataOutputStream dataOutputStream){
        this.socket = socket;
        this.dataOutputStream = dataOutputStream;
    }

    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            String input;
            while(LongClient.connetionState){

                System.out.println("请输入你想发送的异常：");
                input = scanner.nextLine();
                AbnormalJson abmormalJson = new AbnormalJson(input);

                if(abmormalJson.getMessage() != null){
                    MessageProtocol sender = new MessageProtocol();
                    sender.setMessage(abmormalJson.getMessage());
                    System.out.println(abmormalJson.getMessage());
                    System.out.println("the byte array sent:\n" +ProtocolUtil.byte2hex(sender.getData()));
                    ProtocolUtil.writeOutputStream(sender, dataOutputStream);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            try{
                System.out.println("发送线程异常。");
                socket.close();
                LongClient.connetionState = false;
                LongClient.reConnect();
            } catch (Exception ee){
                ee.printStackTrace();
            }
        }

    }
}

//class ClientHeart implements Runnable{
//    private Socket socket;
//    private DataOutputStream dataOutputStream;
//
//    ClientHeart(Socket socket, DataOutputStream dataOutputStream){
//        this.socket = socket;
//        this.dataOutputStream = dataOutputStream;
//    }
//
//    public void run() {
//        try {
//            System.out.println("thread heart beat has been started.");
//            while(true){
//                Thread.sleep(5000);
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            try{
//                socket.close();
//                LongClient.connetionState = false;
//                LongClient.reConnect();
//            } catch (Exception ee){
//                ee.printStackTrace();
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
