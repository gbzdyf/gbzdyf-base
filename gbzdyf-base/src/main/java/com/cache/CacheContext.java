package com.cache;




import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by y on 2016/12/15.
 */
public class CacheContext<T> {

    private Map<String, T> cache = Maps.newConcurrentMap();

    public T get(String key) {
        return cache.get(key);
    }

    public void addAndUpdateCache(String key, T value) {
        cache.put(key, value);
    }

    public void evictCache(String key) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        }
    }

    public void evictCache() {
        cache.clear();
    }
}
