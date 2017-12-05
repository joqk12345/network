package com.standalone.tomcat.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author joqk
 * @Date 2017/12/5 15:08
 * @{description} xxxxx
 **/
public class ServletConfigMapping {
    private static List<JkServletConfig> configList = new ArrayList<>();
    static {
        configList.add(new JkServletConfig("JoqkServlet",
                "/joqk/get",
                "com.standalone.tomcat.servlet.JoqkServlet"));
    }
    public static List<JkServletConfig> getConfigList(){
        return configList;
    }
}
