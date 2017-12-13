package com.horvan.network.jdk5;

import com.horvan.network.jdk5.utils.ServerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

/**
 * @author joqk
 * @Date 2017/11/30 16:18
 * @{description} xxxxx
 **/
public class SocketClient {

    private Socket socket;
    public static Logger logger = LoggerFactory.getLogger(SocketClient.class);

    PrintWriter printWriter;
    InputStream input;
    OutputStream output;
    DataOutputStream dataOutputStream;

    public SocketClient() throws IOException {
        socket = new Socket(ServerUtils.IP, ServerUtils.PORT);
        // 由Socket对象得到输入流，并构造相应的BufferedReader对象
        printWriter = new PrintWriter(socket.getOutputStream());
        input = socket.getInputStream();
        output = socket.getOutputStream();
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    public void initChatServer() {
        //开个线程接收消息
        receiveMessage();
    }

    public void sendMessage(String contentMsg) {
        try {
            if (socket == null) {
                logger.info("未连接服务端");
                return;
            }
            byte[] str = contentMsg.getBytes("utf-8");//将内容转utf-8
            String aaa = new String(str);

            /**
             * 因为服务器那边的readLine()为阻塞读取
             * 如果它读取不到换行符或者输出流结束就会一直阻塞在那里
             * 所以在json消息最后加上换行符，用于告诉服务器，消息已经发送完毕了
             * */
            aaa += "\n";
            logger.info("客户端发送数据:"+aaa);
            output.write(aaa.getBytes("utf-8"));// 换行打印
            output.flush(); // 刷新输出流，使Server马上收到该字符串
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("test", "错误：" + e.toString());
        }
    }

    private void receiveMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 向本机的8000端口发出客户请求
                    socket = new Socket(ServerUtils.IP, ServerUtils.PORT);
                    // 由Socket对象得到输入流，并构造相应的BufferedReader对象
                    printWriter = new PrintWriter(socket.getOutputStream());
                    input = socket.getInputStream();
                    output = socket.getOutputStream();
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    // 从客户端获取信息
                    BufferedReader bff = new BufferedReader(new InputStreamReader(input));
                    // 读取发来服务器信息
                    String line;
                    while (true) {
                        Thread.sleep(500);
                        // 获取客户端的信息
                        while ((line = bff.readLine()) != null) {
                            logger.info("socket", "内容 : " + line);
                        }
                        if (socket == null)
                            break;
                    }
                    output.close();//关闭Socket输出流
                    input.close();//关闭Socket输入流
                    socket.close();//关闭Socket
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("test", "错误：" + e.toString());
                }
            }
        }).start();
    }


    public static void main(String args[]) throws IOException {

        SocketClient mc = new SocketClient();
//        mc.startConnection();
//        mc.CloseConnection();
    }
}
