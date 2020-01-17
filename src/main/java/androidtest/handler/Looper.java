package androidtest.handler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Looper {
    private BlockingQueue<Message> queue = null;
    private static ThreadLocal<Looper> local = new ThreadLocal<>();
    private static Looper mLooper = null;

    public static void prepared() {
        Looper looper = new Looper();
        looper.queue = new ArrayBlockingQueue<>(5);
        local.set(looper);
        //main looper
        if (mLooper == null) {
            mLooper = looper;
        }
    }

    /**
     * 获取当前线程的Looper对象
     *
     * @return looper
     */
    public static Looper myLooper() {
        return local.get();
    }

    /**
     * 获取主线程的Looper对象
     *
     * @return mainLooper
     */
    public static Looper mainLooper() {
        return mLooper;
    }

    /**
     * 循环等待消息队列
     */
    public static void loop() {
        Looper me = Looper.myLooper();
        Message message = null;
        try {
            while ((message = me.queue.take()) != null) {
                message.target.dispatchMessage(message);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 为当前Looper的消息队列添加消息
     *
     * @param message 消息
     */
    public void addMessage(Message message) {
        try {
            queue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
