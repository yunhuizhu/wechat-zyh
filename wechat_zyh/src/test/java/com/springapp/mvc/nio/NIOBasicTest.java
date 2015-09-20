package com.springapp.mvc.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-5-25
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */
public class NIOBasicTest {
    public static void main(String[] args) {
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("F:\\bitbucket-repo\\wechat-zyh\\wechat_zyh\\src\\main\\resource\\system.properties", "rw");

        FileChannel inChannel = aFile.getChannel();
        //分配空间
        ByteBuffer buf = ByteBuffer.allocate(48);
        //从通道写内容,可以使用缓冲区数组，进行多个读与多个写操作
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            //反转，写模式切换到读模式 ，position设为0，limit设为写模式传入的值，capacity为容量
            buf.flip();

            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }
            //clear()方法会清空整个缓冲区。compact()方法只会清除已经读过的数据。
            // 任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
