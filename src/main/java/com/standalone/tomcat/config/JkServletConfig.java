package com.standalone.tomcat.config;

/**
 * @author joqk
 * @Date 2017/12/5 15:03
 * @{description} 类比于ServletName ，
 * javaConfig
 **/
public class JkServletConfig {

    private String name;
    private String urlMapping;
    private String clazz;

    public JkServletConfig(String name, String urlMapping, String clazz) {
        this.name = name;
        this.urlMapping = urlMapping;
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlMapping() {
        return urlMapping;
    }

    public void setUrlMapping(String urlMapping) {
        this.urlMapping = urlMapping;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
