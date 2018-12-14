/**
 *
 */
package com.iot.cache.service;

import java.util.List;

import com.iot.cache.vo.CacheTree;
import com.iot.cache.entity.CacheBean;


/**
 * 解决问题：
 */
public interface ICacheManager {
     void removeAll();

     void remove(String key);

     void remove(List<CacheBean> caches);

     void removeByPre(String pre);

     List<CacheTree> getAll();

     List<CacheTree> getByPre(String pre);

     void update(String key, int hour);

     void update(List<CacheBean> caches, int hour);

     String get(String key);
}
