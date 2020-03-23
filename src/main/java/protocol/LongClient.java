package protocol;

import abnormal_process.AbnormalJson;
import com.alibaba.fastjson.JSONObject;

import java.awt.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;

public class LongClient {
    private static Socket socket;
    public static boolean connetionState = false;
    public static Thread listen;
    public static Thread send;
    public static Integer[] synchronize = new Integer[2];
    public static void main(String args[]){
        while(!connetionState){
            try {
                connect();
//                showThread();
                Thread.sleep(3000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void showThread(){
        while(true) {

            ThreadGroup group = Thread.currentThread().getThreadGroup();
            ThreadGroup topGroup = group;
            // �����߳���������ȡ���߳���
            while (group != null) {
                topGroup = group;
                group = group.getParent();
            }
            // ������߳����ټ�һ������ֹö��ʱ�п��ܸպ��ж�̬�߳�����
            int slackSize = topGroup.activeCount() * 2;
            Thread[] slackThreads = new Thread[slackSize];
            // ��ȡ���߳����µ������̣߳����ص�actualSize�������յ��߳���
            int actualSize = topGroup.enumerate(slackThreads);
            Thread[] atualThreads = new Thread[actualSize];
            // ����slackThreads����Ч��ֵ��atualThreads
            System.arraycopy(slackThreads, 0, atualThreads, 0, actualSize);
            System.out.println("Threads size is " + atualThreads.length);
            for (Thread thread : atualThreads) {
                System.out.println("Thread name : " + thread.getName() + thread.getState());
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void connect() {
        try {
            socket = new Socket(Config.ADDRESS, Config.PORT);
            System.out.println("�ɹ����ӵ���" + socket.getRemoteSocketAddress());
            connetionState = true;
            DataOutputStream dataOutputStream;
            DataInputStream dataInputStream;
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
            listen = new Thread(new ClientListen(socket, dataInputStream));
            send = new Thread(new ClientSend(socket, dataOutputStream));
            listen.start();
            send.start();

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
        System.out.println("�رշ��ͺͽ��ս��̣�");
        if(listen != null){
            listen.interrupt();
            listen = null;
        }
        if(send != null){
            send.interrupt();
//            System.out.print(sendTask.isInterrupted());
            send = null;
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
                System.out.println("�ظ���Ϣ��");
                System.out.println(receiver.getMessage());
                LongClient.synchronize[0]--;
                synchronized(LongClient.synchronize){
                    if(LongClient.synchronize[0] == 0) {
                        System.out.println("free send thread....");
                        LongClient.synchronize.notifyAll();
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
            try{
                System.out.println("�����߳��쳣��");
                socket.close();
                LongClient.stopTask();
                LongClient.connetionState = false;
                LongClient.reConnect();
            } catch (Exception ee){
                ee.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(System.in));
            String input;
            System.out.println("���������뷢�͵��쳣��");
            while(!Thread.interrupted() && LongClient.connetionState){

                if (!br.ready()){
                    try{
                        Thread.sleep(1000);
                        continue;
                    }catch (InterruptedException e) {
                        System.out.println("send task get interrupt...");
                        if (!LongClient.connetionState)
                            socket.close();
                        return;
                    }
                }

                input = br.readLine();

                AbnormalJson abmormalJson = new AbnormalJson(input);

                if(abmormalJson.getMessage() != null){
                    MessageProtocol sender = new MessageProtocol();
                    sender.setMessage(abmormalJson.getMessage());
                    System.out.println(abmormalJson.getMessage());
                    System.out.println("the byte array sent:\n" +ProtocolUtil.byte2hex(sender.getData()));
                    ProtocolUtil.writeOutputStream(sender, dataOutputStream);
                }
                else{
                    continue;
                }
                System.out.println(System.identityHashCode(LongClient.synchronize));
                LongClient.synchronize[0] = 2;
                System.out.println(System.identityHashCode(LongClient.synchronize));

                synchronized (LongClient.synchronize){
                    try{
                        LongClient.synchronize.wait();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("���������뷢�͵��쳣��");

            }
        } catch (IOException e) {
            e.printStackTrace();
            try{
                System.out.println("�����߳��쳣��");
                socket.close();
                LongClient.stopTask();
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
