package com.horvan.network.network2;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;

/**
 * @author joqk
 * @Date 2017/12/3 17:53
 * @{description} xxxxx
 **/
public class Server {
    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("服务器即将启动。。。。。");
            Socket socket =serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String info = null;
            while ((info=bufferedReader.readLine())!=null){
                System.out.println("我是服务器，客户端说"+info);
            }
            socket.shutdownInput();

            //获取输出流，响应客户端请求
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write("欢迎您。！~");
            printWriter.flush();

            //关闭资源
            printWriter.close();
            outputStream.close();
            bufferedReader.close();
            inputStreamReader.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
