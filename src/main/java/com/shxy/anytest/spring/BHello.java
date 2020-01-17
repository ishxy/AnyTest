package com.shxy.anytest.spring;

public class BHello implements IHello {
    @Override
    public void sayHello() {
        System.out.println("B hello");
    }
}
