package com.horvan.network.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    //线程池
    private ExecutorService executorService;
    //线程组
    private AsynchronousChannelGroup asynchronousChannelGroup;
    //服务器通道
    public AsynchronousServerSocketChannel asynchronousSocketChannel;

    public Server(int port){
        try {
            executorService = Executors.newCachedThreadPool();
            asynchronousChannelGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService,1);
            asynchronousSocketChannel = AsynchronousServerSocketChannel.open(asynchronousChannelGroup);
            asynchronousSocketChannel.bind(new InetSocketAddress(port));
            System.out.println("server start,port:"+port);
            asynchronousSocketChannel.accept(this,new ServerCompletionHandler());
            Thread.sleep(Integer.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8379);
    }
}
