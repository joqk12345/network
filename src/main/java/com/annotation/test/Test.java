package com.annotation.test;

import com.annotation.demo.springmvc.Controller;

import java.lang.annotation.Annotation;

/**
 * @author joqk
 * @Date ${date}
 * @{description}
 **/
//@JoqkAnnotation("qk")
public class Test {

//    @JoqkAnnotation("这个是主函数")
    public static void main(String[] args) {
        Class clazz = TestAnnotation.class;
        Controller controller =(Controller) clazz.getAnnotation(Controller.class);
        System.out.println(controller.value());
        //java中有标准的API，定了了一个接口
        Annotation annotation = null;
        //OOP 中有个 is -a 的关系

    }
}

