package com.ava;

import com.google.common.cache.*;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CacheTest {
    @Test
    public void testLoadingCache() throws Exception {
        LoadingCache<String,String> cache = CacheBuilder
                .newBuilder()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return "load..."+key;
                    }
                });
        System.out.println(cache.apply("aaa"));
        System.out.println(cache.get("aaa"));
        System.out.println(cache.get("bbb"));
        System.out.println(cache.apply("bbb"));
        System.out.println(cache.apply("ccc"));
        cache.put("ddd", "ddd");
        System.out.println(cache.get("ddd"));
    }
    @Test
    public void testCallableCache() throws Exception {  //允许在get时候指定
        Cache<String,String> cache = CacheBuilder.newBuilder().maximumSize(1024).build();
        String resultVal = cache.get("aaa", new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "call..."+"aaa";
            }
        });
        System.out.println(resultVal);

        resultVal = cache.get("bbb", new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "call..."+"bbb";
            }
        });
        System.out.println(resultVal);
    }

    /**
     * 不需要延迟加载处理
     * @param cacheLoader
     * @param <K>
     * @param <V>
     * @return
     */
    private  <K,V> LoadingCache<K,V> loadingCache(CacheLoader<K,V> cacheLoader) {
        LoadingCache<K,V> loadingCache = CacheBuilder
                .newBuilder()
                .maximumSize(2)  //设置缓存中key数量
                .weakKeys()  //对key的弱引用
                .weakValues()
                .refreshAfterWrite(120, TimeUnit.SECONDS)  //最后一次更新后给定时间内自动刷新cache
                .expireAfterWrite(10, TimeUnit.MINUTES)  //最后一次更新后给定时间内回收掉
                .removalListener(new RemovalListener<K, V>() {  //移除数据时如果要有其他动作可以自定义RemovalListener,该类中的行为和移除动作同步执行

                    @Override
                    public void onRemoval(RemovalNotification<K, V> notification) {
                        System.out.println(notification.getKey()+" is removed");
                    }
                })
                .build(cacheLoader);
        return loadingCache;
    }

    private LoadingCache<String,String> loadingCacheInstance(final String key) {
        LoadingCache<String,String> loadingCache = loadingCache(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                return "load..."+key;
            }

            /**
             * 此处进行数据库查询并返回结果
             */
        });
        return loadingCache;
    }

    @Test
    public void testLoadingCacheII() throws Exception {
        LoadingCache<String, String> loadingCache = loadingCacheInstance("aaa");
        System.out.println(loadingCache.get("aaa"));
        loadingCache.apply("bbb");
        System.out.println(loadingCache.get("bbb"));
        loadingCache.apply("ccc");
        System.out.println(loadingCache.get("ccc"));
    }

    /**
     * 需要延迟加载处理的
     * @param <K>
     * @param <V>
     * @return
     */
    private  <K,V> Cache<K,V> callableCache() {
        Cache<K,V> cache = CacheBuilder
                .newBuilder()
                .maximumSize(1000)  //cache中的key条目数量，不是指内存大小
                .expireAfterWrite(10,TimeUnit.MINUTES)  //某个键值对被创建或被替换后多少时间移除
                .build();
        return cache;
    }

    private Cache<String, String> cacheFromCallable = null;

    private String getCallableCache(final String key) {
        try {
            return cacheFromCallable.get(key, new Callable<String>() {  //Callable只有在缓存中值不存在时才会调用
                @Override
                public String call() throws Exception {
                    System.out.println("get key: "+key+" from db");
                    return "call..."+key;
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Test
    public void testCallableCacheII() {
        final String key = "aaa";
        final String key1 = "bbb";
        final String key2 = "ccc";
        cacheFromCallable = callableCache();
        System.out.println(getCallableCache(key));
        System.out.println(getCallableCache(key1));
        System.out.println(getCallableCache(key2));
        System.out.println(getCallableCache(key));
    }
}
