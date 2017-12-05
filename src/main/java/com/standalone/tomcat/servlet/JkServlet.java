package com.standalone.tomcat.servlet;

import com.standalone.tomcat.JoqkRequest;
import com.standalone.tomcat.JoqkResponse;

/**
 * @author joqk
 * @Date 2017/12/5 14:56
 * @{description} xxxxx
 **/
public class JkServlet extends JoqkServlet {
    @Override
    public void doGet(JoqkRequest request, JoqkResponse response) {
        response.write("joqk servlet Get response");
    }

    @Override
    public void doPost(JoqkRequest request, JoqkResponse response) {
        response.write("joqk servlet Post response");
    }
}
