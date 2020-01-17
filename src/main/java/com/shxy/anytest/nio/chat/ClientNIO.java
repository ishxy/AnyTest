package com.shxy.anytest.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class ClientNIO {
    public static void main(String[] args) {
        ClientThread thread = new ClientThread();
        thread.conn(new InetSocketAddress("127.0.0.1", 9876));
        thread.start();
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()) {
            thread.sendMsg(cin.next());
        }
    }

    public static class ClientThread extends Thread {
        private ByteBuffer readBuffer = ByteBuffer.allocate(1024);

        private ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

        private SocketChannel socketChannel = null;

        public ClientThread() {

        }

        public void conn(SocketAddress address) {
            try {
                socketChannel = SocketChannel.open();
                socketChannel.configureBlocking(true);
                socketChannel.connect(address);
                System.out.println("client connecting...");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void sendMsg(String msg) {
            System.out.println("send");
            writeBuffer.clear();
            writeBuffer.put(msg.getBytes());
            writeBuffer.flip();
            try {

                socketChannel.write(writeBuffer);

            } catch (IOException e) {
                e.printStackTrace();
            }
            writeBuffer.clear();
        }

        @Override
        public void run() {

            try {
                if (socketChannel.finishConnect()) {
                    System.out.println("connected");
                    while (true) {
                        int readSize = 0;
                        System.out.println("wait");
                        while ((readSize = socketChannel.read(readBuffer)) != 0) {
                            System.out.println(readSize);
                            System.out.println(new String(readBuffer.array(), 0, (int) readSize));
                            readBuffer.clear();
                        }

                    }
                } else {
                    System.out.println("connected failed");
                }
            } catch (IOException e) {

            } finally {
                if (socketChannel != null) {
                    try {
                        socketChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
