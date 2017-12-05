package com.standalone.tomcat;

import com.standalone.tomcat.config.JkServletConfig;
import com.standalone.tomcat.config.ServletConfigMapping;
import com.standalone.tomcat.servlet.JoqkServlet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author joqk
 * @Date 2017/12/5 14:19
 * @{description} 自定义tomcat容器
 * 提供网络服务，socket、nio、netty
 * tomcat 窗口启动类
 **/
public class JoqkTomcat {
    private  int port = 8080;
    //请求的url与具体的实现类之间的映射
    private Map<String,Class<JoqkServlet>> servletMap = new HashMap<String,Class<JoqkServlet>>();

    public JoqkTomcat() {
    }

    public JoqkTomcat(int port) {
        this.port = port;
    }
    public void start(){
        //init，注册Servlet
        initServlets();
        //获取连接
        try {
            ServerSocket serverSocket = new ServerSocket(this.port);
            System.out.println("joqk Tomcat started on:"+this.port);
            while (true){
                Socket socket = serverSocket.accept();
                //封装request
                JoqkRequest joqkRequest = new JoqkRequest(socket.getInputStream());
                JoqkResponse joqkResponse = new JoqkResponse(socket.getOutputStream());
                //分发业务处理类
                dispatch(joqkRequest,joqkResponse);
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化容器，注册servlet
     */
    private void initServlets() {
        for(JkServletConfig jkServletConfig : ServletConfigMapping.getConfigList()){
            try {
                servletMap.put(jkServletConfig.getUrlMapping(),(Class<JoqkServlet>)Class.forName(jkServletConfig.getClazz()));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 分发器
     * @param joqkRequest
     * @param joqkResponse
     */
    private void dispatch(JoqkRequest joqkRequest, JoqkResponse joqkResponse) {
       Class<?> clazz = servletMap.get(joqkRequest.getUrl());
        try {
           if(null != clazz){
                   JoqkServlet servlet =(JoqkServlet) clazz.newInstance();
                   servlet.service(joqkRequest,joqkResponse);
           }else {
               //404
               joqkResponse.write("404 Not Found");
           }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            joqkResponse.write("500 internal error \r\n  "+ Arrays.toString(e.getStackTrace()));
        }
    }

    public static void main(String[] args) {
        new JoqkTomcat().start();
    }
}

