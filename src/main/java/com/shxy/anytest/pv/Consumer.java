package com.shxy.anytest.pv;

public class Consumer extends Thread {

    public Consumer(Goods goods) {
        this.goods = goods;
    }

    private Goods goods;

    @Override
    public void run() {
        while (true) {
            synchronized (goods) {
                while (goods.state != Goods.State.FULL) {
                    try {
                        goods.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("consume goods");
                goods.state = Goods.State.EMPTY;
                goods.notify();
            }
        }
    }
}
