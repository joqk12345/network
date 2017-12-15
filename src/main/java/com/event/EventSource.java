package com.event;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author joqk
 * @Date 2017/12/13 15:34
 * @{description} 事件源
 **/
public class EventSource {
    private Vector vector = new Vector();

    public EventSource() {
    }
    public void addListener(MessageListener messageListener){
        this.vector.add(messageListener);
    }

    public void notifiyEvent(){
        Enumeration enumeration = vector.elements();
        while (enumeration.hasMoreElements()){
            MessageListener messageListener = (MessageListener) enumeration.nextElement();
            messageListener.hanleEvent(new DemoEvent(this));
        }
    }

}
