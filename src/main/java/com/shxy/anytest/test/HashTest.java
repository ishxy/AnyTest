package com.shxy.anytest.test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashTest {
    public static void main(String[] args){
        Map<HashTest,String> map = new ConcurrentHashMap<>(4);
        map.put(new HashTest(),"1");
        map.put(new HashTest(),"1");
        map.put(new HashTest(),"1");
        map.put(new HashTest(),"1");
        map.put(new HashTest(),"1");
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
