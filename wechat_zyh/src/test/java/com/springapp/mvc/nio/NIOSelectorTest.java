package com.springapp.mvc.nio;

import java.io.IOException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-5-25
 * Time: 下午3:14
 * To change this template use File | Settings | File Templates.
 */
public class NIOSelectorTest {
    public static void main(String[] args) {

        try {
            //创建管道，通过sink写数据 ,通过souce通道都数据
//            Pipe pipe = Pipe.open();
//            Pipe.SinkChannel sinkChannel = pipe.sink();
//            Pipe.SourceChannel sourceChannel = pipe.source();

            //udp连接创建，使用receive写数据，send读数据
           /* DatagramChannel channel = DatagramChannel.open();
            channel.socket().bind(new InetSocketAddress(9999));*/
            //serverSocket创建
           /* ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(9999));*/
//            SocketChannel socketChannel =serverSocketChannel.accept();    //监听新进来的连接
            //SocketChannel的创建，read都，write写
//            SocketChannel socketChannel = SocketChannel.open();
//            socketChannel.connect(new InetSocketAddress("http://jenkov.com", 80));
        SelectableChannel channel= DatagramChannel.open();
        Selector selector = null;
            selector = Selector.open();

        channel.configureBlocking(false);
        SelectionKey key = channel.register(selector, SelectionKey.OP_READ);
        while(true) {
            int readyChannels = selector.select();
            if(readyChannels == 0) continue;
            Set selectedKeys = selector.selectedKeys();
            Iterator keyIterator = selectedKeys.iterator();
            while(keyIterator.hasNext()) {
                SelectionKey key1 = (SelectionKey) keyIterator.next();
                if(key1.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.
                } else if (key1.isConnectable()) {
                    // a connection was established with a remote server.
                } else if (key1.isReadable()) {
                    // a channel is ready for reading
                } else if (key1.isWritable()) {
                    // a channel is ready for writing
                }
                keyIterator.remove();
            }
        }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }finally {
            //关闭  ServerSocketChannel
//            serverSocketChannel.close();
            //关闭socketChannel通道
//            socketChannel.close();

        }
    }
}
