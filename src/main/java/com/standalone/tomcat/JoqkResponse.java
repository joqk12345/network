package com.standalone.tomcat;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author joqk
 * @Date 2017/12/5 14:38
 * @{description} xxxxx
 **/
public class JoqkResponse {
    private OutputStream outputStream;

    public JoqkResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * 结果写入
     * @param content
     */
    public void write(String content){
        try {
            outputStream.write(content.getBytes());
        } catch (IOException e) {
            //TODO
            e.printStackTrace();
        }
    }
}
