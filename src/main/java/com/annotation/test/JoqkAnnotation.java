package com.annotation.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author joqk
 * @Date 2017/11/27 16:30
 * @{description} xxxxx
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface JoqkAnnotation {
    //注解中的类型，规定注解类型中除了java的八大基本数据类型以外
    //boolean/byte/short/int/char/long/float/double
    //string
    //class toString 包名.类名
    // Annotation
    //enum 枚举
    Class[] value() default {};

}
