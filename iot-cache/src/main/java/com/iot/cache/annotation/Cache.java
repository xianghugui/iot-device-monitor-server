package com.iot.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.iot.cache.constants.CacheScope;
import com.iot.cache.parser.ICacheResultParser;
import com.iot.cache.parser.IKeyGenerator;
import com.iot.cache.parser.impl.DefaultKeyGenerator;
import com.iot.cache.parser.impl.DefaultResultParser;

/**
 * 开启缓存
 */
@Retention(RetentionPolicy.RUNTIME)
// 在运行时可以获取
@Target(value = {ElementType.METHOD, ElementType.TYPE})
// 作用到类，方法，接口上等
public @interface Cache {
    /**
     * 缓存key menu_{0.id}{1}_type
     */
     String key() default "";

    /**
     * 作用域
     */
     CacheScope scope() default CacheScope.application;

    /**
     * 过期时间
     */
     int expire() default 720;

    /**
     * 描述
     */
     String desc() default "";

    /**
     * 返回类型
     */
     Class[] result() default Object.class;

    /**
     * 返回结果解析类
     *
     * @return
     */
     Class<? extends ICacheResultParser> parser() default DefaultResultParser.class;

    /**
     * 键值解析类
     *
     * @return
     */
     Class<? extends IKeyGenerator> generator() default DefaultKeyGenerator.class;
}
