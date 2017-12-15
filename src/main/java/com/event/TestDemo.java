package com.event;

/**
 * @author joqk
 * @Date 2017/12/13 15:53
 * @{description} xxxxx
 **/
public class TestDemo {
    public static void main(String[] args) {
        //构建事件源
        EventSource eventSource = new EventSource();

        //构建监听器
        MyListener myListener = new MyListener();

        eventSource.addListener(new MessageListener() {
            @Override
            public void hanleEvent(DemoEvent demoEvent) {
                System.out.println(demoEvent.getSource().toString());
            }
        });

        //添加监听器
        eventSource.addListener(myListener);
        //执行监听器中的方法
        eventSource.notifiyEvent();


    }
}
