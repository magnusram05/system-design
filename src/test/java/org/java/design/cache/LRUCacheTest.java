package org.java.design.cache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LRUCacheTest {

    @Test
    void exceedCapacity(){
        LRUCache<String, String> lruCache = new LRUCache<>(2);
        lruCache.put("course-1", "programming");
        lruCache.put("course-2", "computer-arch");
        lruCache.put("course-3", "computer-networks");

        Assertions.assertNull(lruCache.get("course-1"));
        Assertions.assertEquals(lruCache.get("course-2"), "computer-arch");
        Assertions.assertEquals(lruCache.get("course-3"), "computer-networks");
    }

    @Test
    void exceedCapacityRetain1stElement(){
        LRUCache<String, String> lruCache = new LRUCache<>(2);
        lruCache.put("course-1", "programming");
        lruCache.put("course-2", "computer-arch");
        Assertions.assertEquals(lruCache.get("course-1"), "programming");

        lruCache.put("course-3", "computer-networks");

        Assertions.assertEquals(lruCache.get("course-1"), "programming");
        Assertions.assertNull(lruCache.get("course-2"));
        Assertions.assertEquals(lruCache.get("course-3"), "computer-networks");
    }

    @Test
    void getPutGet(){
        LRUCache<String, String> lruCache = new LRUCache<>(1);
        Assertions.assertNull(lruCache.get("course-1"));
        lruCache.put("course-1", "programming");
        Assertions.assertEquals(lruCache.get("course-1"), "programming");
    }

    @Test
    void putGetNullGetNull(){
        LRUCache<String, String> lruCache = new LRUCache<>(1);
        lruCache.put("course-1", "programming");
        lruCache.get("course-2");
        Assertions.assertEquals(lruCache.get("course-1"), "programming");
    }

    @Test
    void put4ItemsOnlyLast2Remain(){
        LRUCache<String, String> lruCache = new LRUCache<>(2);
        lruCache.put("course-1", "programming");
        lruCache.put("course-2", "computer-arch");
        lruCache.put("course-3", "computer-networks");
        lruCache.put("course-4", "algo-ds");
        Assertions.assertNull(lruCache.get("course-1"));
        Assertions.assertNull(lruCache.get("course-2"));
        Assertions.assertEquals(lruCache.get("course-3"), "computer-networks");
        Assertions.assertEquals(lruCache.get("course-4"), "algo-ds");
    }

}