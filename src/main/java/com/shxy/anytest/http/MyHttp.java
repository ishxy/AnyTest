package com.shxy.anytest.http;

import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MyHttp {

    public static void main(String[] args) throws IOException {

        String freeUrl = "https://api.seniverse.com/v3/weather/now.json?key=afmlz62jdx69kmph&location=huhehaote&language=zh-Hans&unit=c";
        String unFreeUrl = "https://api.seniverse.com/v3/weather/grid/now.json?key=afmlz62jdx69kmph&location=39.865927:116.359805";
        URL url = new URL(freeUrl);
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String res = "";
        String line;
        while((line = reader.readLine())!=null){
            res += line;
        }
        System.out.println(res);
    }
}
