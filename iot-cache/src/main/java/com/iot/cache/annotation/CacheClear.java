package com.iot.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.iot.cache.parser.IKeyGenerator;
import com.iot.cache.parser.impl.DefaultKeyGenerator;

/**
 * 解决问题：
 */
@Retention(RetentionPolicy.RUNTIME)//在运行时可以获取  
@Target(value = {ElementType.METHOD, ElementType.TYPE})//作用到类，方法，接口上等
public @interface CacheClear {
    /**
     * 缓存key的前缀
     */
     String pre() default "";

    /**
     * 缓存key
     */
     String key() default "";

    /**
     * 缓存keys
     */
     String[] keys() default "";

    /**
     * 键值解析类
     *
     * @return
     */
     Class<? extends IKeyGenerator> generator() default DefaultKeyGenerator.class;
}
