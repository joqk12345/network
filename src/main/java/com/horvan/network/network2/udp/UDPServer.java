package com.horvan.network.network2.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author joqk
 * @Date 2017/12/3 18:28
 * @{description} xxxxx
 **/
public class UDPServer {
    public static void main(String[] args) throws IOException {

        //创建服务器端DatagramSocket ，指定端口
        DatagramSocket socket = new DatagramSocket(8800);
        //创建数据报，用于接收客户端发送的数据
        byte[] data = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(data,data.length);
        //接收客户端发送的数据
        socket.receive(datagramPacket);//此方法在接收数据的前会阻塞
        //读取数据
        String info = new String(data,0,datagramPacket.getLength());
        System.out.println("我是服务器,客户端说:"+info);
        	/*
		 * 向客户端响应数据
		 */
        //1.定义客户端的地址、端口号、数据
        InetAddress address=datagramPacket.getAddress();
        int port=datagramPacket.getPort();
        byte[] data2="欢迎您!".getBytes();
        //2.创建数据报，包含响应的数据信息
        DatagramPacket packet2=new DatagramPacket(data2, data2.length, address, port);
        //3.响应客户端
        socket.send(packet2);
        //4.关闭资源
        socket.close();
    }
}

