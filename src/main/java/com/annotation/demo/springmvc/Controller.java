package com.annotation.demo.springmvc;

import java.lang.annotation.*;

/**
 * @author joqk
 * @Date ${date}
 * @{description} xxxxx
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
    String value() default "";
}

