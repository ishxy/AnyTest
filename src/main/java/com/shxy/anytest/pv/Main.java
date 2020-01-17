package com.shxy.anytest.pv;

public class Main {
    public static void main(String... args){
        Goods goods = new Goods();
        goods.state = Goods.State.EMPTY;
        Consumer consumer = new Consumer(goods);
        Producer producer = new Producer(goods);

        consumer.start();
        producer.start();
    }
}
