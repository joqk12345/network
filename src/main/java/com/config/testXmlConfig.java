package com.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

/**
 * @author joqk
 * @Date ${date}
 * @{description} 测试xml的一个类
 **/
public class testXmlConfig {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(testXmlConfig.class);
        long start  = System.currentTimeMillis();
        Configuration config = null;
        try {
            config = new XMLConfiguration("trial.xml");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        long end  = System.currentTimeMillis();
        logger.debug("加载配置耗时:"+(end-start));
        logger.info("获取根节点的值为"+config.getString("colors.text"));
//        logger.info("获取根节点的值为"+config.getString("log4j.appender.system.out"));
        List<Object> buttonNames = config.getList("buttons.name");
        for (Iterator iter = buttonNames.iterator(); iter.hasNext();)
        {
            String name = (String)iter.next();
          logger.info("迭代循环name的值:"+name);
        }
    }
}
