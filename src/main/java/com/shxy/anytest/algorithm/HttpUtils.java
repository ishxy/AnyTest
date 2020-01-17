package com.shxy.anytest.algorithm;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
public class HttpUtils {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "https://hbe.ink:8443/PrivateFood/api?cate_type=菜式&flag=getcategorybytype";
        System.out.println(sendGet(s));
    }
    //https://hbe.ink:8443/PrivateFood/api?flag=login&username=...&password=...

    public static String sendGet(String path) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
//            path = new String(path.getBytes("gbk"),"gbk");
            System.out.println(path);
            URL url = new URL(path);
            URLConnection conn =  url.openConnection();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();//返回登陆结果
    }
}