package com.horvan.network.jdk5.test;

import com.horvan.network.jdk5.SocketClient;

import java.io.IOException;

/**
 * @author joqk
 * @Date 2017/11/30 16:35
 * @{description} xxxxx
 **/
public class TestSocket {
    public static void main(String[] args)throws IOException {

        for (int i = 0; i < 10 ; i++) {
            new Thread(() ->{
                SocketClient socketClient = null;
                try {
                    socketClient = new SocketClient();
                    socketClient.initChatServer();
                    socketClient.sendMessage("12345656");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }).start();
        }

    }
}
