package com.annotation.test;

import java.lang.annotation.Annotation;

/**
 * @author joqk
 * @Date 2017/11/27 15:03
 * @{description} xxxxx
 **/
public class MyAnnotation implements Annotation {
    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
