package androidtest.handler;

public class Message {
    private CallBack callBack = null;//回调
    public int what = 0;//消息值
    Handler target;//发送消息的andler

    private Message() {
    }

    public static Message obtain(Handler handler, int what) {

        return obtain(handler, what, null);
    }

    public static Message obtain(Handler handler, int what, CallBack callBack) {
        Message message = new Message();
        message.what = what;
        message.target = handler;
        message.setCallBack(callBack);
        return message;
    }

    public Message(CallBack callBack, int what) {
        this.callBack = callBack;
        this.what = what;
    }

    public CallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public interface CallBack {
        void call();
    }

}
