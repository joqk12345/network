package com.horvan.network.bytebuffer;

import com.horvan.network.NettyClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.IntBuffer;

public class IntBufferTest {
    static Logger logger = LoggerFactory.getLogger(IntBufferTest.class);
    public static void main(String[] args) {
//        TestIntbuffer();
        //2、wrap方法的使用
//        wrapTest();

        otherByteBuffer();
    }

    private static void otherByteBuffer() {
        IntBuffer buffer = IntBuffer.allocate(10);
        int[] arr = new int[]{1, 2, 3};
        buffer.put(arr);
        System.out.println("调用put(arr)方法后的buffer：" + buffer);
        //一种复制方法，buffer1的pos、lim、cap与buffer的一样
        IntBuffer buffer1 = buffer.duplicate();
        System.out.println("buffer1：" + buffer1);

        buffer.position(1); //将buffer的position设置为1，不建议使用。功能相当于flip()方法，但是从运行结果可以看出，lim依然等于10
        System.out.println("调用position()方法后的buffer：" + buffer);
        System.out.println("buffer的可读数据量：" + buffer.remaining()); //计算出从pos到lim的长度
        int[] arr1 = new int[buffer.remaining()];
        //将缓冲区的数据放入arr1中
        buffer.get(arr1);
        for(Integer i : arr1) {
            System.out.print(Integer.toString(i) + ",");
        }
        System.out.println();

        //比较flip()方法和position(index)方法的区别
        buffer1.flip();
        System.out.println("buffer1的可读数量：" + buffer1.remaining());
        arr1 = new int[buffer1.remaining()];
        buffer1.get(arr1);
        for(Integer i : arr1) {
            System.out.print(Integer.toString(i) + ",");
        }
    }

    private static void wrapTest() {
        int[] arr = new int[]{1, 2, 3};
        IntBuffer buffer = IntBuffer.wrap(arr);
        System.out.println("wrap(arr)方法：" + buffer);
        //IntBuffer.wrap(array, postion, length)表示容量为array的长度，但是可操作的元素为位置postion到length的数组元素
        buffer = IntBuffer.wrap(arr, 0, 2);
        System.out.println("wrap(arr, 0, 2)：" + buffer);
    }

    private static void TestIntbuffer() {
        //1、基本操作
        //创建指定长度的缓冲区
        IntBuffer buffer = IntBuffer.allocate(10);
        buffer.put(11); //position位置：0->1
        buffer.put(5); //position位置：1->2
        buffer.put(32); //position位置：2->3
        System.out.println("未调用flip复位方法前的buffer：" + buffer +buffer.get(0));
        //把位置复位为0，也就是position位置由3->0
        buffer.flip();
        // 因为缓冲区中已有11、5、32三个元素，也就意味着put()方法会使position向后递增1
        System.out.println("调用flip复位方法后的buffer：" + buffer);
        System.out.println("buffer容量为：" + buffer.capacity());
        System.out.println("buffer限制为：" + buffer.limit());
        System.out.println("获取下标为1的元素：" + buffer.get(1));
        System.out.println("获取下标为1的元素：" + buffer.get(1));
        System.out.println("获取下标为1的元素：" + buffer.get(1));
        System.out.println("调用get(index)方法后的buffer：" + buffer); //调用get(index)方法，不会改变position的值
        buffer.put(1, 4); //将buffer位置为1的值替换为4，调用put(index，value)不会改变position的值
        System.out.println("调用put(index, value)方法后的buffer：" + buffer);

        for(int i=0; i<buffer.limit(); i++) {
            //调用get方法会使缓冲区的位置(position)向后递增一位
            System.out.print(buffer.get() + "\t");
        }
        System.out.println("\nbuffer对象遍历之后buffer为：" + buffer);
    }
}
