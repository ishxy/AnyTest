package androidtest.handler;

public class Handler {
    private Looper looper = null;

    public Handler() {
        looper = Looper.myLooper();
    }

    public void sendMessage(Message message) {
        looper.addMessage(message);
    }

    public void sendEmptyMessage(int what) {
        Message message = Message.obtain(this, what);
        looper.addMessage(message);
    }

    /**
     * 分发消息处理
     * @param message
     */
    public void dispatchMessage(Message message) {
        if (message.getCallBack() != null) {
            message.getCallBack().call();
        } else {
            handlerMessage(message);
        }
    }

    /**
     * 处理消息
     * @param message
     */
    protected void handlerMessage(Message message) {
    }
}
