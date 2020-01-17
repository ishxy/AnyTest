package com.shxy.anytest.nio;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class Test {

    public static void main(String[] args) throws IOException {
        File file = new File("e:\\temp\\answer.txt");
        FileInputStream fin = new FileInputStream(file);
        FileChannel channel = fin.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(16);

        int realsize = 0;
        while ((realsize = channel.read(buffer))!=-1){
           // System.out.println(realsize);
            String s = new String(buffer.array(),0,realsize);
            System.out.print(s);
           // System.out.println("postion = " + channel.position());
           // channel.position(channel.position() + realsize);
            buffer.rewind();
        }
        channel.close();
        fin.close();
    }
}
