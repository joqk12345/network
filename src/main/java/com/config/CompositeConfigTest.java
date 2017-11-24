package com.config;

import org.apache.commons.configuration.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

/**
 * @author joqk
 * @Date ${date}
 * @{description} 组合配置文件测试
 **/
public class CompositeConfigTest {
    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(testConfig1.class);
        long start  = System.currentTimeMillis();
        CompositeConfiguration config = null;
        try {
            config = new CompositeConfiguration();
            long end  = System.currentTimeMillis();
            config.addConfiguration(new PropertiesConfiguration("log4j.properties"),true);
            config.addConfiguration(new XMLConfiguration("trial.xml"),true);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        long end  = System.currentTimeMillis();
        logger.debug("加载配置耗时:"+(end-start));
        logger.info("获取根节点的值为"+config.getString("log4j.rootLogger"));
        logger.info("获取根节点的值为"+config.getString("log4j.appender.system.out"));

        List<Object> buttonNames = config.getList("buttons.name");
        for (Iterator iter = buttonNames.iterator(); iter.hasNext();)
        {
            String name = (String)iter.next();
            logger.info("迭代循环name的值:"+name);
        }
    }
}
