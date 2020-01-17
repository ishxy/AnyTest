package com.shxy.anytest.block;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockQueue {

    public static void main(String[] args) {
        MyBlockQueue queue = new MyBlockQueue(5);
        for (int i = 0; i < 10; i++) {
            new Thread(new CTask(queue)).start();
        }
        new Thread(new PTask(queue)).start();
    }

    static class CTask implements Runnable {
        private MyBlockQueue queue;

        public CTask(MyBlockQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                Object o = queue.take();
            }
        }
    }

    static class PTask implements Runnable {
        private MyBlockQueue queue;
        private int i = 0;

        public PTask(MyBlockQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                queue.put("p:" + i++);
            }
        }
    }

    private Object[] items;
    private int front;
    private int rear;
    private int size;
    private int capacity;
    private Lock mainLock = new ReentrantLock(true);
    private final Condition notEmpty = mainLock.newCondition();
    private final Condition notFull = mainLock.newCondition();

    public MyBlockQueue(int c) {

        capacity = c;
        items = new Object[c];
        front = 0;
        rear = 0;
        size = 0;
    }

    private void enQeueu(Object o) {
        items[rear] = o;
        if (++rear == capacity)
            rear = 0;
        size++;
        System.out.println("放:" + o);
        notEmpty.signal();
    }

    private Object deQueue() {
        Object x = items[front];
        items[front] = null;
        if (++front == capacity)
            front = 0;
        size--;
        System.out.println("取:" + x);
        notFull.signal();
        return x;
    }

    public Object take() {
        mainLock.lock();
        try {
            while (size == 0)
                notEmpty.await();
            return deQueue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mainLock.unlock();
        }
        return null;
    }

    public void put(Object o) {
        mainLock.lock();
        try {
            while (size == capacity)
                notFull.await();
            enQeueu(o);
        } catch (InterruptedException e) {

        } finally {
            mainLock.unlock();
        }
    }

}
