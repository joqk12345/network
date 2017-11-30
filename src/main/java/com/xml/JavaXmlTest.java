package com.xml;
import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import org.jdom.*;
import org.jdom.input.*;
/**
 * @author joqk
 * @Date 2017/11/30 14:41
 * @{description} xxxxx
 **/
public class JavaXmlTest {
    public static void main(String[] args) {
        long lasting = System.currentTimeMillis();
        try {
            SAXBuilder builder = new SAXBuilder();
            File f = new File(JavaXmlTest.class.getResource("/").getPath());
            String path = f.toString();
            System.out.println(f);
            Document doc = builder.build(new File(path+"/IFLFY4AAGRIObserveCalendar20171129.xml"));
            Element foo = doc.getRootElement();
            List allChildren = foo.getChildren();
            for (int i = 0; i < allChildren.size(); i++) {
                System.out.print("任务号码:"+ ((Element) allChildren.get(i)).getChild("TaskNumber").getText());
                System.out.println("任务编号:"+ ((Element) allChildren.get(i)).getChild("TaskCode").getText());
                String arrName2 =   ((Element) allChildren.get(i)).getChild("Params").getName();
//                System.out.print("name"+arrName+"name1"+arrName1+"arrName2:"+arrName2);
                if(arrName2 == "Params"){
                    List params = ((Element) allChildren.get(i)).getChild("Params").getChildren();
//                    System.out.println(params.size());
//                    Stream stream = params.stream();
//                    stream.forEach(System.out::println);
                    for (int j = 0; j < params.size() ; j++) {
                        String startLine =((Element)params.get(j)).getText();
                        String name =((Element)params.get(j)).getName();
                        System.out.println("param参数名:"+name+"，参数值:"+startLine);
//                        System.out.println("startColumnNo:"+startColumnNo);
                    }
                }

//                 List params =  allChildren.get(i).getChild("Params").getChildren();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
