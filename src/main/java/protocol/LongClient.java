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
    public static void main(String args[]){
        connect();
    }

    private static void connect() {
        try {
            socket = new Socket(Config.LOCAL_ADDRESS, Config.PORT);
            connetionState = true;
            DataOutputStream dataOutputStream;
            DataInputStream dataInputStream;
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
            new Thread(new ClientListen(socket, dataInputStream)).start();
            new Thread(new ClientSend(socket, dataOutputStream)).start();
//            new Thread(new ClientHeart(socket, dataOutputStream)).start();
        } catch (IOException e) {
            e.printStackTrace();
            connetionState = false;
            reConnect();
        }
    }

    public static void reConnect(){
        while(!connetionState){
            System.out.println("Trying to reconnect.");
            connect();
            try{
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }

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
            while(true){
                MessageProtocol receiver = (MessageProtocol)ProtocolUtil.readInputStream(dataInputStream);
                System.out.println(receiver.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
            try{
                System.out.println("正在尝试重连：");
                socket.close();
                LongClient.connetionState = false;
                LongClient.reConnect();
            } catch (Exception ee){
                ee.printStackTrace();
            }
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
            while(true){
                Scanner scanner = new Scanner(System.in);
                System.out.println("请输入你想发送的异常：");
                String abnormalType = scanner.nextLine();
                MessageProtocol sender = new MessageProtocol();
                AbnormalJson abmormalJson = new AbnormalJson(abnormalType);
                sender.setMessage(abmormalJson.getMessage());
                ProtocolUtil.writeOutputStream(sender, dataOutputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try{
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
