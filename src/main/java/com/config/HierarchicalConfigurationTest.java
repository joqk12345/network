package com.config;

import org.apache.commons.configuration.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

/**
 * @author joqk
 * @Date ${date}
 * @{description} 有等级关系的配置文件,可以截取子书
 * 支持定时刷新、xpath、import方式。xxx
 **/
public class HierarchicalConfigurationTest {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(HierarchicalConfigurationTest.class);
        long start  = System.currentTimeMillis();
        HierarchicalConfiguration sub = null;
        XMLConfiguration config =null;
        try {
            config = new XMLConfiguration("hierarch.xml");
            sub = config.configurationAt("tables.table(0)",true);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        long end  = System.currentTimeMillis();
        logger.debug("加载配置耗时:"+(end-start));

//        logger.info("获取根节点的值为"+config.getString("log4j.rootLogger"));
//        logger.info("获取根节点的值为"+config.getString("log4j.appender.system.out"));
        System.out.println("name:"+sub.getString("name"));
        //遍历某个节点
        List<Object> fieldNames = sub.getList("fields.field.name");
        for (int i = 0; i <fieldNames.size() ; i++) {
            System.out.println(fieldNames.get(i));
        }
        System.out.println(config.getKeys("table"));
        //最大索引数是-1，目前没有值，还未初始化
        System.out.println( config.getMaxIndex("table"));
        Iterator<String> iterator = config.getKeys("table");
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
