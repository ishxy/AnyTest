package com.shxy.anytest.pv;

public class Producer extends Thread{
    private Goods goods;

    public Producer(Goods goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        while (true){
            synchronized (goods){
                while(goods.state!= Goods.State.EMPTY){
                    try {
                        goods.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("product goods");
                goods.state = Goods.State.FULL;
                goods.notifyAll();
            }
        }
    }
}
