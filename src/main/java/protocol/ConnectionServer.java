package protocol;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.IOException;
import java.net.ServerSocket;

public class ConnectionServer {

    private static boolean isStart = true;
    private static LongServer longServer;
    public static void main(String[] args){

        ServerSocket serverSocket=null;
        ExecutorService executorService=Executors.newCachedThreadPool();
        try {
            serverSocket=new ServerSocket(Config.PORT);
            while (isStart) {
                Socket socket = serverSocket.accept();
                String userIP=socket.getInetAddress().getHostAddress();
                System.out.println("用户的IP地址为：" + userIP);
                longServer = new LongServer(socket);

                if (socket.isConnected()) {
                    executorService.execute(longServer);
                }
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket!=null){
                try {
                    isStart=false;
                    serverSocket.close();
                    if(serverSocket!=null)
                        longServer.stops();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    }
