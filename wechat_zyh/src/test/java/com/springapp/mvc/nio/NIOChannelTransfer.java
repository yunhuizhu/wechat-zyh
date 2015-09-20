package com.springapp.mvc.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-5-25
 * Time: 下午3:07
 * To change this template use File | Settings | File Templates.
 */
public class NIOChannelTransfer {
    public static void main(String[] args) {
        try {
            RandomAccessFile fromFile = null;
            fromFile = new RandomAccessFile("F:\\bitbucket-repo\\wechat-zyh\\wechat_zyh\\src\\main\\resource\\system.properties", "rw");

        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("C:\\Users\\zhu\\Desktop\\23231231.txt", "rw");
        FileChannel      toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

//        toChannel.transferFrom(fromChannel,position, count );
        fromChannel.transferTo(position, count, toChannel);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
