package com.shxy.anytest.nio.chat;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientIO {
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
        private Socket mSocket = null;
        private PrintWriter writer = null;
        private BufferedReader reader = null;

        public void conn(InetSocketAddress inetSocketAddress) {
            try {
                mSocket = new Socket();
                mSocket.connect(inetSocketAddress);
                System.out.println("conn");
                writer = new PrintWriter(new OutputStreamWriter(mSocket.getOutputStream()));
                reader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendMsg(String next) {
            System.out.println("send : " + next);
            writer.println(next);
            writer.flush();
        }

        @Override
        public void run() {
            while (true) {
                String line = "";
                try {
                    while((line = reader.readLine())!=null){
                        System.out.println("msg from server :" + line);
                    }
                } catch (IOException e) {

                    e.printStackTrace();
                    System.exit(-1);
                }
            }
        }
    }
}
