package com.horvan.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server implements Runnable {
    private Selector selector;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    public Server(int port) {
        try {
            //打开多路复用器
            selector  = Selector.open();
            //打开服务器通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //设置服务器通道为非阻塞
            serverSocketChannel.configureBlocking(false);
            //绑定地址
            serverSocketChannel.bind(new InetSocketAddress(port));
            //把服务器通道注册到多路复用给其上，并监听阻塞状态
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server start port:"+port);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void run() {
        while (true){
            try {
                //让多路复用器开始监听
                selector.select();
                //返回所有已经注册到多路复用选择器上的通道的SelectionKey
               Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
               // 遍历keys
                while (keys.hasNext()){
                    SelectionKey key = keys.next();
                    keys.remove();
                    if(key.isValid()){//如果key的状态是有效的
                        if(key.isAcceptable()){//如果key是阻塞状态，则调用accept()方法
                            accept(key);
                        }
                        if(key.isReadable()){
                            read(key);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void accept(SelectionKey key){

        try {
            //获取服务器通道
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            //2 执行阻塞方法
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector,SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read(SelectionKey key){

        try {
            //1 清空缓冲区中的旧数据
            byteBuffer.clear();
            //2 获取之前注册的SocketChannel通道
            SocketChannel socketChannel = (SocketChannel)  key.channel();
            //3 将sc中的数据放入buffer中
            int count = socketChannel.read(byteBuffer);
            if(count == -1){
                key.channel().close();
                key.cancel();
                return;
            }
            //读取到了数据，将buffer的position复位到0
            byteBuffer.flip();

            byte[] bytes = new byte[byteBuffer.remaining()];
            //将buffer中的数据写入byte[]中
            byteBuffer.get(bytes);
            String body = new String(bytes).trim();
            System.out.println("Server：" + body);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Thread(new Server(8379)).start();
    }
}
