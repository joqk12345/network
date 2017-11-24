package com.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationFactory;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author joqk
 * @Date ${date}
 * @{description} 工厂方法创建配置文件
 * A factory class that creates a composite configuration from an XML based configuration definition file.
 * This class provides an easy and flexible means for loading multiple configuration sources and combining the results into a single configuration object.
 **/
public class ConfigurationFactoryTest {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(testConfig1.class);
        long start  = System.currentTimeMillis();
//        ConfigurationFactory fty = new ConfigurationFactory("init.xml");

        Configuration config =null;
        try {
            DefaultConfigurationBuilder defaultConfigurationBuilder = new DefaultConfigurationBuilder("init.xml");
            config = defaultConfigurationBuilder.getConfiguration();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        long end  = System.currentTimeMillis();
        logger.debug("加载配置耗时:"+(end-start));
        logger.info("获取根节点的值为"+config.getString("log4j.rootLogger"));
        logger.info("获取根节点的值为"+config.getString("log4j.appender.system.out"));
    }
}
