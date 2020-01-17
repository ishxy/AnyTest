package com.shxy.anytest.ThreadPool;

import org.junit.Test;

public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);

                } catch (InterruptedException e) {
                    System.out.println("interrupt");
                    e.printStackTrace();

                }
            }
        });
        thread.start();
        Thread.sleep(1000);
        Thread.currentThread().interrupt();
        System.out.println("thread = " + thread.isInterrupted());

        Thread.sleep(1000);
    }

    @Test
    public void unit(String s){
        System.out.println(s);
    }
}
