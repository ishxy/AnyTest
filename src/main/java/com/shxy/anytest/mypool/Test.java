package com.shxy.anytest.mypool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    public static void main(String[] args) throws InterruptedException {
//        MyThreadPool pool = new MyThreadPool(5);
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 1; i++) {
            pool.submit(new Task1("shxy " + i));
        }

        Thread.sleep(3000);

        pool.shutdownNow();

//        Thread t = new Thread(new Task1("shxy " + 1));
//        t.start();
//        t.interrupt();
    }

    private static class Task1 implements Runnable{

        private String name;

        public Task1(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("task : " + name + "  is running ");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.err.println(1111111);
                e.printStackTrace();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.err.println(22222);
                e.printStackTrace();
            }
            System.out.println("task : " + name + "  is finished ");
        }
    }
}
