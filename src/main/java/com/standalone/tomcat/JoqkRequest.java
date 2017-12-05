package com.standalone.tomcat;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author joqk
 * @Date 2017/12/5 14:38
 * @{description} xxxxx
 **/
public class JoqkRequest {
    private InputStream inputStream;
    private String url;
    private String method;

    public JoqkRequest(InputStream inputStream) {
        this.inputStream = inputStream;
        String content = null;
        byte[] buff = new byte[1024];
        int len = 0;
        try {
            if((len = inputStream.read(buff))>0){
                content = new String(buff,0,len);
            }
            extractFileds(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 以换行
     * 以空格为分割
     * @param content
     */
    private void extractFileds(String content) {
        String command = content.split("\\n")[0];
        String words[] = command.split("\\s");
        setMethod(words[0]);
        setUrl(words[1]);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
