package com.shxy.anytest.mypool;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class MyThreadPool {
    private int coreSize;
    private int currentIndex;
    private List<MyThread> coreThreads;
    private BlockingQueue<Runnable> taskQueue;

    MyThreadPool(int coreSize) {
        this.coreSize = coreSize;
        currentIndex = 0;
        coreThreads = new LinkedList<MyThread>();
        taskQueue = new LinkedBlockingDeque<>();
    }

    public synchronized void submit(Runnable task) {
        if (currentIndex < coreSize) {
            try {
                taskQueue.put(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MyThread thread = new MyThread(taskQueue);
            coreThreads.add(thread);
            thread.start();
            currentIndex++;
        } else {
            try {
                taskQueue.put(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        for (MyThread thread : coreThreads) {
            thread.interrupt();
        }
    }
}
