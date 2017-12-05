package com.standalone.tomcat.servlet;

import com.standalone.tomcat.JoqkRequest;
import com.standalone.tomcat.JoqkResponse;

/**
 * @author joqk
 * @Date 2017/12/5 14:53
 * @{description} xxxxx
 **/
public abstract class JoqkServlet {

    public abstract  void doGet(JoqkRequest request, JoqkResponse response);
    public abstract  void doPost(JoqkRequest request,JoqkResponse response);
    public  void service(JoqkRequest request, JoqkResponse response){
        if("GET".equals(request.getMethod())){
            doGet(request,response);
        }else {
            doPost(request,response);
        }
    }
}
