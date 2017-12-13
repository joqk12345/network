package com.horvan.network.network2.mutiThread;


import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

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
            Socket socket =null;
            int count = 0;
            //循环监听等待客户端连接
            while (true){
                //调用accept
                socket  =serverSocket.accept();
                //创建一个新线程
                ServerThread serverThread = new ServerThread(socket);
                //设置优先级，范围为【1-10】，默认为5，未设置优先级会导致线程运行时速度慢
                serverThread.setPriority(4);
                serverThread.start();
                count ++;
                System.out.println("客户端的数量为:"+count);
                InetAddress address = socket.getInetAddress();
                System.out.println("当前客户端的IP:"+address.getHostAddress());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
