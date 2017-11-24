package com.poi;

/**
 * @author joqk
 * @Date ${date}
 * @{description} xxxxx
 **/
public class Test07 {
    public static void main(String[] args) {
        String path = "D:\\data\\poi\\";
        String fileName = "poi.docx";
        String filePath = path + fileName;
        //创建word
        WordUtils.createWord(path,fileName);
        //写入数据
        String data = "本文是以poi3.9读写2010word、2010excel、2010ppt,记录学习的脚步相应的功能在代码都有注释,就不解释了 详情可以参看poi3.9的文档主测试函数 TestMain.java";
        WordUtils.writeDataDocx(filePath,data,true,12);
        //WordUtils.writeDataDoc(filePath,data);

        //读取数据
        //String contentWord=WordUtils.readDataDoc(filePath);
        String contentWord=WordUtils.readDataDocx(filePath);
        System.out.println("word的内容为:\n"+contentWord);
        System.out.println();
    }
}
