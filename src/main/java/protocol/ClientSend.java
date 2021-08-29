package main.java.protocol;

import main.java.abnormal_process.AbnormalJson;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientSend implements Runnable {
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private LinkedBlockingQueue<AbnormalJson> abnormalQueue;

    ClientSend(Socket socket, DataOutputStream dataOutputStream, LinkedBlockingQueue<AbnormalJson> abnormalQueue) {
        this.socket = socket;
        this.dataOutputStream = dataOutputStream;
        this.abnormalQueue = abnormalQueue;
    }

    @Override
    public void run() {
        try {
            System.out.println("请输入你想发送的异常：");
            while (!Thread.interrupted() && LongClient.connetionState && !this.abnormalQueue.isEmpty()) {
                AbnormalJson abnormalJson = this.abnormalQueue.take();
                if (abnormalJson.getMessage() != null) {
                    MessageProtocol sender = new MessageProtocol();
                    sender.setMessage(abnormalJson.getMessage());
                    System.out.println(abnormalJson.getMessage());
                    System.out.println("the byte array sent:\n" + ProtocolUtil.byte2hex(sender.getData()));
                    ProtocolUtil.writeOutputStream(sender, dataOutputStream);
                } else {
                    continue;
                }
                System.out.println(System.identityHashCode(LongClient.synchronize));
                LongClient.synchronize[0] = 2;
                System.out.println(System.identityHashCode(LongClient.synchronize));

                synchronized (LongClient.synchronize) {
                    try {
                        LongClient.synchronize.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("请输入你想发送的异常：");

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
