package com.shxy.anytest.randomFile;

import java.io.*;

public class Test {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        String inPath = "E:\\迅雷下载\\platform_frameworks_base-master.zip";
        int r = inPath.lastIndexOf(".");
        String outPath;
        StringBuilder builder = new StringBuilder(inPath);
        builder.insert(r,"shxy");
        outPath = builder.toString();
        File file = new File(inPath);
        System.out.println(file.length());
        MultiDownloadTask downTask = new MultiDownloadTask(inPath, file.length(),
                5, outPath);
        downTask.load();
        System.out.println(System.currentTimeMillis()/1000);
        while (!downTask.isDone()){
            System.out.println("no yet done");
            Thread.sleep(1000);
        }
        //
        System.out.println("done");
    }
}
