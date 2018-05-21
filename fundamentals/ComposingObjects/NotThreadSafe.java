package com.concurrency.fundamental.composingobjects;

import java.lang.annotation.*;

/**
 * @author xwiam
 * @create 2018/04/20 14:12
 * @desc definite a notThreadSafe interface
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
@Documented
public @interface NotThreadSafe {

}
