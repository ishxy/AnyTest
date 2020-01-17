package com.shxy.anytest.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class FirstSpring {
    private IHello hello;

    public IHello getHello() {
        return hello;
    }

    public void setHello(IHello hello) {
        this.hello = hello;
    }

    public static void main(String[] args) {

        ApplicationContext context = new FileSystemXmlApplicationContext("src/bean.xml");
        FirstSpring firstSpring = (FirstSpring) context.getBean("first");
        firstSpring.hello.sayHello();
    }
}
