package protocol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;


public class LongServer implements Runnable {
    private static String[] string = {"You successfully transferred."};

    //��Ϣ����
    private volatile ConcurrentLinkedQueue<BasicProtocol> receiverData = new ConcurrentLinkedQueue<BasicProtocol>();
    private volatile ConcurrentLinkedQueue<BasicProtocol> senderData= new ConcurrentLinkedQueue<BasicProtocol>();
    private WriteTask writeTask;//д���ݵ��߳�
    private ReadTask readTask;//�����ݵ��߳�
    private Socket socket;

    public LongServer(Socket socket){
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            readTask = new ReadTask();
            writeTask = new WriteTask();


            writeTask.outputStream = new DataOutputStream(socket.getOutputStream());//Ĭ�ϳ�ʼ�������Լ�
            readTask.inputStream = new DataInputStream(socket.getInputStream());


            readTask.start();
            writeTask.start();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * �����ȡ����
     */
    public class ReadTask extends Thread{
        private DataInputStream inputStream;
        private boolean isCancle = false;//�Ƿ�ȡ��ѭ��
        @Override
        public void run() {
            try {
                while (!isCancle){
                    BasicProtocol protocol = ProtocolUtil.readInputStream(inputStream);
                    if(protocol != null){
                        System.out.println("================:"+protocol.getMessage());
                        protocol.setMessage(string[0]);
                        senderData.add(protocol);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally{
                stops();//����io�쳣������ԭ�������ӶϿ��ˣ���������ͣ�����в���
                System.out.println("�����߳��쳣���ѶϿ����ӡ�");
            }
        }
    }

    /**
     * ����д������
     */
    public class WriteTask extends Thread{
        private DataOutputStream outputStream;
        private boolean isCancle = false;
//        private Timer heart = new Timer();//�����������Ķ�ʱ����
//        private Timer message = new Timer();//ģ�ⷢ����ͨ����
        @Override
        public void run() {
            //ÿ��20s����һ��������
//            heart.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    reciverData.add(new (BasicProtocol)HeartBeatProtocol() {
//                    });
//                }
//            },0,1000*20);

//            //����ʱ2s��Ȼ��ÿ��6s����һ����ͨ����
//            final Random random = new Random();
//            message.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    MessageProtocol bp = new MessageProtocol();
//                    bp.setMessage(string[random.nextInt(string.length)]);
//                    receiverData.add(bp);
//                }
//            },1000*2,1000*20);

            try {
                while (!isCancle){
                    BasicProtocol bp = senderData.poll();
                    if(bp!=null){
                        System.out.println("------:"+bp.getMessage());
                            ProtocolUtil.writeOutputStream(bp,outputStream);
                        }
                    }
            }catch (IOException e) {
                e.printStackTrace();
            } finally{
                System.out.println("�����߳��쳣�� �ѶϿ����ӡ�");
                stopWrite();
            }
        }
    }

    /**
     * ֹͣ�����л
     */
    public void stops(){
        stopRead();
        stopWrite();
    }
    public void stopRead(){
        if (readTask!=null){
            readTask.isCancle=true;
            readTask.interrupt();
            readTask=null;
        }
    }
    public void stopWrite(){
        if (writeTask!=null) {
            writeTask.isCancle = true;
//            //ȡ�������������Ķ�ʱ����
//            writeTask.heart.cancel();
            //ȡ��������ͨ��Ϣ�Ķ�ʱ����
//            writeTask.message.cancel();
            writeTask.interrupt();
            writeTask=null;
        }
    }
}