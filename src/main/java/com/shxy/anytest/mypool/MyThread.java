package com.shxy.anytest.mypool;

import java.util.concurrent.BlockingQueue;


public class MyThread extends Thread {

    private BlockingQueue<Runnable> taskQueue;

    public MyThread(BlockingQueue<Runnable> q) {
        this.taskQueue = q;
    }

    @Override
    public void run() {
        Runnable task = null;
        try {
            while ((task = taskQueue.take()) != null) {
                task.run();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("thread shutdown");
        }

    }
}
