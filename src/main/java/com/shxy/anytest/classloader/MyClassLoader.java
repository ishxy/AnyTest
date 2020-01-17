package com.shxy.anytest.classloader;

import java.io.*;

public class MyClassLoader extends ClassLoader {

    private String libPath;

    public MyClassLoader(String path) {
        this.libPath = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File filename = new File(libPath, getFileName(name));
        InputStream in = null;
        ByteArrayOutputStream bao = null;
        try {
            in = new FileInputStream(filename);

            bao = new ByteArrayOutputStream();
            int len;
            byte b;
            while ((len = in.read()) != -1) {
                b = (byte) (len ^2);
                bao.write(b);
            }
            byte[] buff = bao.toByteArray();

            return defineClass(name, buff, 0, buff.length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                bao.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return super.findClass(name);
    }

    private static String getFileName(String name) {
        int i = name.lastIndexOf(".");
        return name.substring(i+1, name.length()) + ".class";
    }

    public static void encryptionClassFile(String path) {
        File file = new File(path);
        InputStream in = null;
        OutputStream out = null;
        ByteArrayOutputStream bao = null;
        try {
            in = new FileInputStream(path);
            bao = new ByteArrayOutputStream();
            int b;
            while ((b = in.read()) != -1) {
                bao.write(b ^ 2);
            }
            in.close();
            out = new FileOutputStream(path);
            out.write(bao.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                out.close();
                bao.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}