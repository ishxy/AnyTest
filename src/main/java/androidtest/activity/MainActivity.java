package androidtest.activity;

import androidtest.handler.Handler;
import androidtest.handler.Message;

public class MainActivity extends Activity {

    private static final int MSG_TEST = 1;
    Handler handler = new Handler() {
        @Override
        protected void handlerMessage(Message message) {
            switch (message.what) {
                case MSG_TEST:
                    System.out.println("Thread name = " + Thread.currentThread().getName() + " resolve message");
                    break;
            }
        }
    };

    @Override
    protected void onCreate() {
        super.onCreate();
        System.out.println("onCreate");
        Thread workThread = new Thread(new Task());
        workThread.setName("working thread");
        workThread.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
