package com.shxy.anytest.classloader.reference;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * UsingClass内部有静态成员变量 ReferenceClass实例
 *
 * classLoader加载UsingClass时，不会加载其内部的类对象
 * 调用getDeclaredField方法后，会加载这个成员对应的类对象
 */
public class ReferenceStarter {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ReferenceTestClassLoader classLoader = new ReferenceTestClassLoader();
        Class<?> UsingClass = classLoader.loadClass("com.shxy.anytest.classloader.reference.UsingClass");
        Field field = UsingClass.getDeclaredField("mReferenceClass");
        field.setAccessible(true);
        Object mReferenceObject = field.get(null);
        Class<?> referenceClass = Class.forName("com.shxy.anytest.classloader.reference.ReferenceClass",false,classLoader);
//        Class<?> referenceClass = classLoader.loadClass("com.shxy.anytest.classloader.reference.ReferenceClass");
        Method method = referenceClass.getDeclaredMethod("test", null);
        method.setAccessible(true);
        method.invoke(mReferenceObject, null);
    }

    private static class ReferenceTestClassLoader extends ClassLoader {
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            File classFile = new File("resource", getFileName(name));
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(classFile));
                byte[] buff = new byte[(int) classFile.length()];
                bufferedInputStream.read(buff, 0, buff.length);
                return defineClass(name, buff, 0, buff.length);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return super.findClass(name);
        }
    }

    private static String getFileName(String name) {
        int i = name.lastIndexOf(".");
        return name.substring(i + 1, name.length()) + ".class";
    }
}
