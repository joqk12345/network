package com.horvan.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoleilu.hutool.Hutool;
import com.xiaoleilu.hutool.lang.Console;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import com.xiaoleilu.hutool.log.StaticLog;
import com.xiaoleilu.hutool.log.level.Level;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "开始进入系统了!" );
        StaticLog.info("This is static {} log.", "INFO");
        StaticLog.debug("this test {} ",Level.DEBUG);
        Log log = LogFactory.get();
        log.debug("This is {} log", "default");
        Console.log("----------------------------------------------------------------------");
    }
}
