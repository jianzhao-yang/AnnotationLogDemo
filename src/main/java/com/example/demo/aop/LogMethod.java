package com.example.demo.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface LogMethod {
    /**
     * 定义日志输入记录哪些入参,防止有文件或大数据量参数,导致记录过多无用数据
     * @return
     */
    String inputKey() default "";
}
