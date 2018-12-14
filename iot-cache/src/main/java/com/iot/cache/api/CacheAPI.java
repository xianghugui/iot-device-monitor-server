package com.iot.cache.api;

import java.util.List;

import com.iot.cache.entity.CacheBean;

/**
 * 缓存API
 */
public interface CacheAPI {
    /**
     * 传入key获取缓存json，需要用fastjson转换为对象

     */
    public String get(String key);

    /**
     * 保存缓存
     */
    public void set(String key, Object value, int expireMin);

    /**
     * 保存缓存
     */
    public void set(String key, Object value, int expireMin, String desc);

    /**
     * 移除单个缓存
     */
    public Long remove(String key);

    /**
     * 移除多个缓存
     */
    public Long remove(String... keys);

    /**
     * 按前缀移除缓存
     */
    public Long removeByPre(String pre);

    /**
     * 通过前缀获取缓存信息
     */
    public List<CacheBean> getCacheBeanByPre(String pre);

    /**
     * 获取所有缓存对象信息
     */
    public List<CacheBean> listAll();

    /**
     * 是否启用缓存
     */
    public boolean isEnabled();

    /**
     * 加入系统标志缓存
     */
    public String addSys(String key);
}
