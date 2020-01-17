package com.shxy.anytest.classloader;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

        MyClassLoader.encryptionClassFile("e:\\temp\\Test.class");
        ClassLoader cLoader = new MyClassLoader("e:\\temp\\");
        Class<?> clazz = cLoader.loadClass("com.shxy.anytest.classloader.Test");

        Object c = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("OOOOO", String.class);
        method.invoke(c, "from 反射");


    }


}
