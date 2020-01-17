package com.shxy.anytest.nio.chat;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Vector;

public class Server {
    private static final int BUF_SIZE = 1024;
    private static final int PORT = 9876;
    private static final int TIMEOUT = 3000;
    private static Vector<SelectionKey> mListSocket = new Vector<>();

    private static ServerSocketChannel ssChannel = null;
    private static Selector selector = null;

    public static void main(String[] args) {

        try {
            ssChannel = ServerSocketChannel.open();
            ssChannel.socket().bind(new InetSocketAddress(PORT));
            ssChannel.configureBlocking(false);
            System.out.println("server on port:" + PORT);

            selector = Selector.open();
            ssChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("register selector");
            while (true) {
                if (selector.select(TIMEOUT) == 0) {
                    System.out.println("wait for new action ...");
                    continue;
                }
                //System.out.println("get action");
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                System.out.println("action size = " + selector.selectedKeys().size());
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    if (key.isAcceptable()) {
                        handleAccept(key);
                    }
                    if (key.isReadable()) {
                        handleRead(key);
                    }
                    if (key.isWritable() && key.isValid()) {
                        handleWirte(key);
                    }
                    if (key.isConnectable()) {
                        System.out.println("isConnectable = true");
                    }
                    iter.remove();
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {

            try {
                if (selector != null) {
                    selector.close();
                }
                if (ssChannel != null) {
                    ssChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    private static void handleWirte(SelectionKey key) throws IOException {
        System.out.println("write action");
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.flip();
        System.out.println(new String(buffer.array()));
        while (buffer.hasRemaining()) {
            sc.write(buffer);
        }
        buffer.compact();
        sc.socket().getOutputStream().flush();
        key.interestOps(SelectionKey.OP_READ);
    }

    private static void handleRead(SelectionKey key) throws IOException {
        System.out.println("read action");
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        long readSize = sc.read(buffer);
        StringBuilder msg = new StringBuilder();
        while (readSize > 0) {
            buffer.flip();
            msg.append(new String(buffer.array(), 0, (int) readSize));
            buffer.clear();
            readSize = sc.read(buffer);
        }
        System.out.println(msg);
        Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
        while(iter.hasNext()){
            SelectionKey sKey = iter.next();
            System.out.println(msg.toString());
            sKey.attach(ByteBuffer.wrap(msg.toString().getBytes()));
            sKey.interestOps(sKey.interestOps() | SelectionKey.OP_WRITE);
        }
//        if (readSize == -1) {
//            System.out.println("read close");
//            sc.close();
//        }
    }

    private static void handleAccept(SelectionKey key) throws IOException {
        System.out.println("accept action");
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        SocketChannel sc = channel.accept();
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(BUF_SIZE));
        mListSocket.add(key);
    }
}
