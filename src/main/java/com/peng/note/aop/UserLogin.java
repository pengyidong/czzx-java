package com.peng.note.aop;

import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * @Author : code
 * @Date 2022/2/20 10:47
 * @Version 1.0
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Order(1)
public @interface UserLogin {

}
