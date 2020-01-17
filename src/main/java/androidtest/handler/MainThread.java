package androidtest.handler;

import androidtest.activity.ActivityBean;
import androidtest.activity.ParserTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainThread {

    public static void main(String[] args) {
        MainThread mainThread = new MainThread();
        Thread.currentThread().setName("main thread");
        mainThread.run();

    }

    private static final int MSG_TEST = 1;
    private ParserTest parserTest = new ParserTest();
    private Handler handler = null;
    public void run() {
        Looper.prepared();
        parserTest.paserManifest();
        ActivityBean launcherBean = parserTest.getlauncher();
        try {
            Class<?> clazz = Class.forName(launcherBean.getName());
            Method method = clazz.getDeclaredMethod("onCreate");
            method.setAccessible(true);
            method.invoke(clazz.newInstance());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Looper.loop();
    }

    /**
     * 模仿activity
     */
    private void likeActivity() {
        System.out.println("onCreate()");
        System.out.println("onStart()");
        System.out.println("onResume()");
        handler = initHandler();
        Thread workThread = new Thread(new Task());
        workThread.setName("working thread");
        workThread.start();
    }

    private Handler initHandler() {
        return new Handler() {
            @Override
            protected void handlerMessage(Message message) {
                switch (message.what) {
                    case MSG_TEST:
                        System.out.println("Thread name = " + Thread.currentThread().getName() + " resolve message");
                        break;
                }
            }
        };
    }

    private class Task implements Runnable {

        @Override
        public void run() {
            int n = 10;
            while (n-- > 0) {
                try {
                    Thread.sleep(2000);
                    System.out.println("Thread name = " + Thread.currentThread().getName() + " send message!");
                    handler.sendEmptyMessage(MSG_TEST);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
