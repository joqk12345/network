package com.horvan.network.jdk5;

import com.horvan.network.jdk5.utils.ServerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author joqk
 * @Date 2017/11/30 16:25
 * @{description} 服务器
 **/
public class SocketServer {

    public static Logger logger = LoggerFactory.getLogger(SocketServer.class);
    private ServerSocket serverSocket;
    private Socket s;
    public OutputStream os;

    //构造函数
    public SocketServer() {

    }

    /**
     * 初始化服务器
     */
    public void initServer(){
        try {
            serverSocket = new ServerSocket(ServerUtils.PORT);
            logger.info("初始化服务端服务器。。。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建消息管理器
     * @throws IOException
     */
    public void CreateMessageManager() throws IOException {
        logger.info("等待用户接入。。。");
        // 使用accept()阻塞等待客户请求
        Socket socket = serverSocket.accept();
        logger.info("用户IP : " + socket.getInetAddress()+" 用户端口:"+socket.getPort());
        // 开启一个子线程来等待另外的socket加入
        new Thread(()->{
            try {
                CreateMessageManager();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        //输出给客户端信息
        OutputStream outputStream = socket.getOutputStream();
        //获取客户短信息
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        new Thread(()->{
            try {
                String buffer;
                while (true) {
                    // 从控制台输入
                    BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
                    buffer = strin.readLine();
                    // 因为readLine以换行符为结束点所以，结尾加入换行
                    buffer += "\n";
                    outputStream.write(buffer.getBytes("utf-8"));
                    // 发送数据
                    outputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        String line = null;
        while (true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while ((line =bufferedReader.readLine())!=null){
                logger.info("收到的数据为:"+line);
            }

        }
    }

    public void setConnection() throws IOException{
        //建立服务器端的Socket

        try{ //ServerSocke.accept()t返回一个Socket对象
            s=serverSocket.accept();
            os=s.getOutputStream();
            os.write("hello".getBytes());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void closeConnection(){
        if(os!=null){
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
      if(s!=null){
          try {
              s.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
    }


    public static void main(String[] args) throws IOException {

        SocketServer ms=new SocketServer();
//        ms.setConnection();
        ms.initServer();
        ms.CreateMessageManager();

    }



}
