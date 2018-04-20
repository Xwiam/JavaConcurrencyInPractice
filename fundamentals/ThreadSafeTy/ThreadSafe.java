package com.concurrency.fundamental.threadSafe;

import java.lang.annotation.*;

/**
 * @author xwiam
 * @create 2018/04/20 14:31
 * @desc definite a threadsafe interface
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
@Documented
public @interface ThreadSafe {
}
