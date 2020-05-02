import java.nio.ByteBuffer;

/**参考资料：https://blog.csdn.net/zxm1306192988/article/details/60581173
 * 缓冲区 在java nio中负责数据的存取。缓冲区就是数组。用于存储不同数据类型的数据
 * 根据类型不同（boolean 除外），提供了相应类型的缓冲区
 * byteBuffer CharBuffer shortBuffer intBuffer longBuffer floatBuffer doubleBuffer
 * 通过allocate()获取缓冲区
 * 缓冲区去存数据的两个核心方法：put()存入数据到缓冲区 get()获取缓冲区的数据
 * capacity：容量，表示缓冲区中最大存储数据的容量。一旦声明不能改变。
 * limit：界限，表示缓冲区中可以操作数据的大小。(limit后数据不能进行读写)
 * position：位置，表示缓冲区中正在操作数据的位置。
 *
 */
public class BufferTest {
    public void test1(){
        String str="abcde";
        //设置缓冲区大小
        ByteBuffer buff=ByteBuffer.allocate(1024);
        System.out.println("缓冲区大小:"+buff.capacity());
        System.out.println("缓冲区界限："+buff.limit());
        System.out.println("缓冲区位置："+buff.position());
        //2.利用put()存放数据到缓冲区中
        buff.put(str.getBytes());
        System.out.println("put==============================");
        System.out.println("缓冲区大小:"+buff.capacity());
        System.out.println("缓冲区界限："+buff.limit());
        System.out.println("缓冲区位置："+buff.position());
        //3.切换读取数据模式
        buff.flip();
        System.out.println("flip=========================");
        System.out.println("缓冲区大小:"+buff.capacity());
        System.out.println("缓冲区界限："+buff.limit());
        System.out.println("缓冲区位置："+buff.position());
        //4.利用get()读取缓冲区中的数据
        byte[] bytes=new byte[buff.limit()];
        buff.get(bytes);
        System.out.println("读取到的内容："+new String(bytes));
        System.out.println("get()=========================");
        System.out.println("缓冲区大小:"+buff.capacity());
        System.out.println("缓冲区界限："+buff.limit());
        System.out.println("缓冲区位置："+buff.position());
        //5.rewind():可重复读
        buff.rewind();
        System.out.println("--------------rewind()------------");
        System.out.println("缓冲区大小:"+buff.capacity());
        System.out.println("缓冲区界限："+buff.limit());
        System.out.println("缓冲区位置："+buff.position());
        //6.clear():清空缓冲区。但是缓冲区中的数据依然存在，但是处在“被遗忘”状态
        buff.clear();
        System.out.println("clear()======================");
        System.out.println("缓冲区大小:"+buff.capacity());
        System.out.println("缓冲区界限："+buff.limit());
        System.out.println("缓冲区位置："+buff.position());
        System.out.println((char)buff.get());
    }
    public void test2(){
        java.lang.String s="abcde";
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        byteBuffer.put(s.getBytes());
        byteBuffer.flip();
        byte[] bytes=new byte[byteBuffer.limit()];
        byteBuffer.get(bytes,0,2);
        System.out.println("获取到的数据"+new java.lang.String(bytes));
        System.out.println("缓冲区位置："+byteBuffer.position());
        byteBuffer.mark();
        byteBuffer.get(bytes,2,2);
        System.out.println("-获取到的数据"+new java.lang.String(bytes));
        System.out.println("-缓冲区位置："+byteBuffer.position());
        byteBuffer.reset();
        System.out.println("--获取到的数据"+new java.lang.String(bytes));
        System.out.println("--缓冲区位置："+byteBuffer.position());
        //判断缓冲区中是否还有剩余数据
        if(byteBuffer.hasRemaining()){
            //获取缓冲区中可以操作的数量
            System.out.println(byteBuffer.remaining());
        }
    }
    public void test3(){
        ByteBuffer byteBuffer=ByteBuffer.allocateDirect(1024);
        //判断是不是直接缓冲区
        System.out.println(byteBuffer.isDirect());
    }

    public static void main(String[] args) {
        BufferTest bufferTest=new BufferTest();
        bufferTest.test3();
    }
}
