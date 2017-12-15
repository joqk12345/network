package com.event;

import java.util.EventObject;

/**
 * @author joqk
 * @Date 2017/12/13 15:45
 * @{description} demo事件
 **/
public class DemoEvent extends EventObject {
    //source—事件源对象—如在界面上发生的点击按钮事件中的按钮
    //所有 Event 在构造时都引用了对象 "source"，在逻辑上认为该对象是最初发生有关 Event 的对象
    public DemoEvent(Object source) {
        super(source);
    }

    public  void say(){
        System.out.println("this  is say method。。。。。");
    }
}
