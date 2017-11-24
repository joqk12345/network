package com.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author joqk
 * @Date ${date}
 * @{description} 测试apache 的配置文件类
 **/
public class testConfig1 {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(testConfig1.class);
        long start  = System.currentTimeMillis();
        Configuration config = null;
        try {
            config = new PropertiesConfiguration("log4j.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        long end  = System.currentTimeMillis();
        logger.debug("加载配置耗时:"+(end-start));
        logger.info("获取根节点的值为"+config.getString("log4j.rootLogger"));
        logger.info("获取根节点的值为"+config.getString("log4j.appender.system.out"));

    }
}
