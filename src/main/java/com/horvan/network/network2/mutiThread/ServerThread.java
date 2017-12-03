package com.horvan.network.network2.mutiThread;

import java.io.*;
import java.net.Socket;

/**
 * @author joqk
 * @Date 2017/12/3 18:10
 * @{description} 服务器端线城处理类
 **/
public class ServerThread extends  Thread {
    //和本线城相关的Socket
    Socket socket = null;
    public ServerThread(Socket socket){
        this.socket = socket;
    }

    //执行线城操作，响应客户端请求

    @Override
    public void run() {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader= null;
        OutputStream outputStream = null;
        PrintWriter printWriter = null;
        try {
            inputStream = socket.getInputStream();
            inputStreamReader  = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String info = null;
            while ((info=bufferedReader.readLine())!=null){
                System.out.println("我是服务器，客户端说"+info);
            }
            socket.shutdownInput();
            //获取输出流，响应客户端请求
            outputStream  = socket.getOutputStream();
            printWriter= new PrintWriter(outputStream);
            printWriter.write("欢迎您。！~");
            printWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            try {
            if (printWriter!=null)
               printWriter.close();
            if (socket!=null)
                socket.close();
            if (outputStream!=null)
                outputStream.close();
            if (bufferedReader!=null)
                bufferedReader.close();
            if (inputStreamReader!=null)
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
