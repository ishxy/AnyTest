package com.shxy.anytest.test;

import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test1 {

    @FunctionalInterface
    public interface Listener {
        void onClick(String s, int i);
    }

    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public static class ListenerImpl implements Listener {

        @Override
        public void onClick(String s, int i) {

        }
    }

    public static void main(String[] args) throws IOException {
//        String[] arr = new String[]{"yes", "YES", "no", "NO"};
//        Arrays.stream(arr).map(x -> x.toLowerCase()).forEach(System.out::println);
        String[] arr1 = {"d", "b", "c", "d"};
        String[] arr2 = {"e", "f", "c", "d"};
        String[] arr3 = {"h", "j", "c", "d"};
        // Stream.of(arr1, arr2, arr3).flatMap(x -> Arrays.stream(x)).forEach(System.out::println);
//        Stream.of(arr1, arr2, arr3).flatMap(Arrays::stream).forEach(System.out::println);
//        Stream.iterate(1,x->x+2).limit(5).forEach(System.out::println);
        Stream.of(arr1).max(Comparator.comparing(String::length)).ifPresent(System.out::println);
//        String s = new String();
//        s.intern();
//        ThreadLocal<Integer> local = new ThreadLocal<>();
//        local.set(1);
//        System.out.println(local.get());
//        local.set(2);
//        System.out.println(local.get());
//        IntStream.range(0, 10).forEach(x -> {
//
//        });
//        Test1 t = new Test1();
//        t.setListener((a, b) -> {
//            System.out.println(a);
//            System.out.println(b);
//        });
//        t.setListener(new ListenerImpl()::onClick);
    }

}
