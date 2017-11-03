package com.horvan.network.bytebuffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    public static void main(String[] args) {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1",8379);
        SocketChannel socketChannel = null;
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(address);
            while (true){
                byte[] bytes = new byte[1024];
                System.in.read( bytes);
                byteBuffer.put(bytes);
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(socketChannel!=null){
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
