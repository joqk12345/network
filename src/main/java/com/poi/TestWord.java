package com.poi;

import org.apache.poi.POIDocument;
import org.apache.poi.hpsf.HPSFPropertiesOnlyDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * HSSF - 提供读写Microsoft Excel格式档案的功能。
 * XSSF - 提供读写Microsoft Excel OOXML格式档案的功能。
 * HWPF - 提供读写Microsoft Word格式档案的功能
 * HSLF - 提供读写Microsoft PowerPoint格式档案的功能。
 * HDGF - 提供读写Microsoft Visio格式档案的功能。
 * POIFS (较差混淆技术实现文件系统) : 此组件是所有其他POI元件的基本因素。它被用来明确地读取不同的文件。
 * XWPF (XML字处理器格式) : 它是用来读取和写入MS-Word的docx扩展名的文件
 * HPBF (可怕的出版商格式) : 它被用来读取和写入MS-Publisher文件。
 * poi也有两个不同的jar包，分别是处理excel2003和excel2007+的，对应的是poi和poi-ooxml。毕竟poi-ooxml是poi的升级版本，处理的单页数据量也是百万级别的，所以我们选择的也是poi-ooxml。
 */
public class TestWord {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(TestWord.class);
        System.out.println("hello poi");
        logger.info("开始测试导出poi");
//        POIDocument poiDocument = new HPSFPropertiesOnlyDocument();
        String destFile="D:\\11.doc";

        Map<String, String> map=new HashMap<String, String>();

        map.put("name", "Zues");
        map.put("sex", "男");
        map.put("idCard", "200010");
        map.put("year1", "2000");
        map.put("month1", "07");
        map.put("year2", "2008");
        map.put("month2", "07");
        map.put("gap", "2");
        map.put("zhuanye", "计算机科学与技术");
        map.put("type", "研究生");
        map.put("bianhao", "2011020301");
        map.put("nowy", "2011");
        map.put("nowm", "01");
        map.put("nowd", "20220301");


    }
}
