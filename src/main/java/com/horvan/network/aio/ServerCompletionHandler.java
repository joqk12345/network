package com.horvan.network.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class ServerCompletionHandler implements CompletionHandler<AsynchronousSocketChannel,Server> {
    @Override
    public void completed(AsynchronousSocketChannel result, Server attachment) {
        //当有下一个客户端接入的时候，直接调用Server的accept方法，这样反复执行下去，保证多个客户端都可以阻塞
        attachment.asynchronousSocketChannel.accept(attachment,this);
        read(result);
    }

    private void read(AsynchronousSocketChannel channel) {
        //读取数据
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer resultSize, ByteBuffer attachment) {
                attachment.flip();
                System.out.println("Server->" + "收到客户端发送的数据长度为：" + resultSize);
                String data = new String(buffer.array()).trim();
                System.out.println("Server->" + "收到客户端发送的数据为：" + data);
                String response = "服务器端响应了客户端。。。。。。";
                write(channel, response);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
            }
        });
    }

    private void write(AsynchronousSocketChannel channel, String response) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(response.getBytes());
            buffer.flip();
            channel.write(buffer).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, Server attachment) {
        exc.getMessage();
    }
}
