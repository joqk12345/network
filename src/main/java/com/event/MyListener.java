package com.event;

/**
 * @author joqk
 * @Date 2017/12/13 15:47
 * @{description} xxxxx
 **/
public class MyListener implements MessageListener{

    @Override
    public void hanleEvent(DemoEvent demoEvent) {
        demoEvent.say();
    }
}
