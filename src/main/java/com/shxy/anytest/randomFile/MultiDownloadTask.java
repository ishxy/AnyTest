package com.shxy.anytest.randomFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiDownloadTask {

    private int threadCount;
    private ExecutorService threadPool;
    private long fileSize;
    private String outPath;
    private String inPath;
    private List<Future> futures;

    public MultiDownloadTask(String inPath, long fileSize, int threadCount, String outPath) {
        this.threadCount = threadCount;
        this.fileSize = fileSize;
        this.outPath = outPath;
        this.inPath = inPath;
        futures = new ArrayList<>();
    }

    public void load() {
        threadPool = Executors.newFixedThreadPool(threadCount);
        long eachPartLength = fileSize / threadCount;
        long modLength = fileSize % threadCount;

        for (int i = 0; i < threadCount; i++) {
            DownloadThread call = null;
            if (i == threadCount - 1) {
                call = new DownloadThread(i,eachPartLength * i, eachPartLength + modLength, outPath);
            } else {
                call = new DownloadThread(i,eachPartLength * i, eachPartLength, outPath);
            }
            Future future = threadPool.submit(call);
            futures.add(future);
        }
    }

    class DownloadThread implements Callable<Boolean> {

        private long startPos;
        private long length;
        private long completeLength;
        private RandomAccessFile file;
        private int id;

        public DownloadThread(int id,long startPos, long length, String path) {
            try {
                this.id = id;
                this.length = length;
                this.startPos = startPos;
                file = new RandomAccessFile(path, "rw");
                file.seek(startPos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Boolean call() throws Exception {
            System.out.println("No." + getId() + " startPos:" + startPos + " length:" + length);
            InputStream in = new FileInputStream(inPath);
            in.skip(startPos);
            int completeLength = 0;
            int readSize = 0;
            byte[] buff = new byte[1024];
            while (completeLength < length && (readSize = in.read(buff)) != -1) {
                completeLength += readSize;
                file.write(buff,0,readSize);
            }
            System.out.println("No." + getId() + "  done");
            file.close();
            in.close();
            System.out.println(System.currentTimeMillis()/1000);
            return true;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public boolean isDone(){
        for(int i = 0; i< futures.size(); i++){
            if (!futures.get(i).isDone()){
                return false;
            }
        }
        return true;
    }
}
