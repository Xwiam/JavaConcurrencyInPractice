package com.concurrency.fundamental.cancellatonandshutDown;

import java.lang.annotation.*;

/**
 * @author xwiam
 * @create 2018/05/20 15:27
 * @desc definite a guardedBy interface
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE,ElementType.FIELD})
@Inherited
@Documented
public @interface GuardedBy {
    String value() default "";
}
