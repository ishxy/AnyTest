package com.shxy.anytest.lambda;

public class LamdbaTest {
    private  interface Listener{
        void f(String s);
    }
    private Listener listener;
    private  void setListener(Listener listener){
        this.listener = listener;
    }
    public static void main(String[] args){
        LamdbaTest lamdbaTest = new LamdbaTest();
        lamdbaTest.setListener(s -> {
            System.out.println(s);
        });
    }
}
