package com.shxy.anytest.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread thread1 = new Thread(new LockRunnable("1"));
        Thread thread2 = new Thread(new LockRunnable("2"));
        Thread thread3 = new Thread(new LockRunnable("3"));
        thread1.start();
//        thread3.start();
        thread2.start();
        Thread.sleep(1000);
        thread2.interrupt();

    }

    public static void oldTest() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 30,
                TimeUnit.MINUTES, new LinkedBlockingDeque<>());
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                Thread.currentThread().sleep(5000);
                System.out.println("123");
                return 1;
            }
        };
        Future<Integer> future;
        future = executor.submit(callable);
        System.out.println("wait");
        future.get();
        System.out.println("finish");
    }

    public static void runnableTest() throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        Future<?> f1 = threadPool.submit(new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        while (!f1.isDone()) {
            Thread.sleep(1000);
            System.out.println("false");
        }
        System.out.println(f1.get());
    }

    public static void callableTest() throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        Future<String> f1 = threadPool.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                return "success";
            }
        });
        while (!f1.isDone()) {
            Thread.sleep(1000);
            System.out.println("false");
        }
        System.out.println(f1.get());
        threadPool.shutdownNow();
    }

    public static void futureTaskTest() {
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "success";
            }
        });
    }

    static Lock lock = new ReentrantLock();

    public static void lockTest(String name) throws InterruptedException {

//        if(!lock.tryLock()){
//            System.out.println("failed return");
//            return;
//        }

        lock.lockInterruptibly();
        for (int i = 0; i < 5; i++) {
            System.out.println(name + " : " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lock.unlock();
    }

    public static class LockRunnable implements Runnable {
        String name;

        public LockRunnable(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                lockTest(name);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "interrupt");
                e.printStackTrace();
            }
        }
    }

}
