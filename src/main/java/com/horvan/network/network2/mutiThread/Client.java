package com.horvan.network.network2.mutiThread;

import java.io.*;
import java.net.Socket;

/**
 * @author joqk
 * @Date 2017/12/3 17:50
 * @{description} xxxxx
 **/
public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",8888);
            OutputStream os = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(os);
            printWriter.write("用户名：admin;密码:123");
            printWriter.flush();
            socket.shutdownOutput();
            //获取输入流
            InputStream inputStream = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String info = null;
            while ((info=br.readLine())!=null){
                System.out.println("我是客户端，服务器端说"+info);
            }

            //关闭相关资源
            br.close();
            inputStream.close();
            printWriter.close();
            os.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
