package com.event;

import java.util.EventListener;

/**
 * @author joqk
 * @Date 2017/12/13 15:39
 * @{description} 消息监听器
 **/
public interface MessageListener  extends  EventListener {
    //Evente是所有事件侦听接口必须扩展的接口，他是一个无内容的接口
    public void hanleEvent(DemoEvent demoEvent);

}
