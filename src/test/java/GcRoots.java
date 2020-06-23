public class GcRoots {
    //哪些可以作为gcroots
    //虚拟机栈（方法局部变量）中引用的对象
    //方法区中静态变量引用的对象
    //方法区中常量引用的对象
    //本地方法栈(native)中引用的对象
    //jvm参数 （标配参数、x参数、xx参数）
    //jps -l
    //jinfo -flag PrintGCDetails 7396 (7396是jps -l 中查到的进程号);jinfo -flags 7396可以查看所有的参数
    //xx参数boolean +开启-关闭,公式：-XX:-（+）PrintGCDetails;kv设值型 公式：-xx:key=value
    //xx:MaxTenuringThreshold=15多少次清除后到老年区
    //xx:MetaspaceSize=128m
    //-Xms等价于：-XX:initialHeapSize -Xmx等价于-XX:MaxHeapSize
    //查看默认值 java -XX:+PrintFlagsInitial;查看修改过后的java -XX:+PrintFlagsFinal -version，=和:=区别，
    // :=是人为或jvm根据不同机器修改过的 java -XX:+PrintCommandLineFlags -version//最后一个参数 -XX:+UseParallelGC是垃圾回收器的参数
    /**
     * 常用参数：-Xms:初始大小内存，等价于：-XX:initialHeapSize，默认为物理内存1/64
     *-Xmx:最大分配内存，默认为物理内存1/4，等价于-XX:MaxHeapSize
     * -Xss:设值单个线程栈的大小，一般默认为512k-1024k,等价于-XX：ThreadStackSize
     * -Xmn:设值年轻代大小
     * -XX:MetaspaceSize:设值元空间大小
     * -XX:+PrintGCDetails：打印gc详细信息 gc名称 gc前 gc后 总内存大小
     * -XX:SurvivorRatio：设置新生代中eden和s0/s1空间的比列，默认8:1:1，默认8
     * -XX:NewRatio：设置老年代占比默认2，即新生代：老年代=1:2
     * -XX:MaxTenuringThreshold：垃圾回收次数最大15，默认15 ，0-15
     */
    /**
     *  GC overhead limit gc回收时间过长抛出outOfMemoryError,大部分时间都在回收垃圾
     * Direct buffer memory nio中容易产生，写nio程序经常使用ByteBuffer来读取或写入数据，这是一种基于channel和buffer的io,在本地内存中写，容易产生此异常
     *不断分配本地内存，堆内存很少使用，那么jvm就不再需要执行gc，directbytebuffer对象就不会被回收，这时候堆内存充足，但本地内存可能已经用光了，再次尝试分配本地
     * 内存就会出现outOfMemoryError，程序直接崩溃了
     *unable to create new native thread高并发中容易发生，不能创建更多的线程了
     * metaspace
     * /
     /**串行垃圾回收器serial
     并行垃圾回收器parallel
     *并发垃圾回收器cms 初始标记、并发标记、重新标记、开始清除 1.3停2、4步不停   并发收集停顿低；并发执行对cpu压力大、采用的标记清除算法会导致大量碎片
     * G1
     */
    /**
     * github 搜索技巧 in 关键字 （1）shopping in:name（2）shopping in:name,readme名字或readme包含shopping
     * stars 关键字 springboot stars:>=5000 点赞数大于等于5000
     * forks 关键字 springboot forks:>=5000 fork数大于等于5000
     * 区间范围 组合使用 springboot forks:100..200 stars:100..200
     * awesome 关键字 学习主题
     *  前后地址后加#L21,21是代码行号，可以直接定位到代码，高亮显示
     * https://github.com/JeffLi1993/springboot-learning-example/blob/master/chapter-3-spring-boot-web/src/main/java/demo/springboot/service/impl/BookServiceImpl.java
     * https://github.com/JeffLi1993/springboot-learning-example/blob/master/chapter-3-spring-boot-web/src/main/java/demo/springboot/service/impl/BookServiceImpl.java#L21
     t快捷键文章内搜索  location:beijing language:java搜索北京java大佬
     */
    /**
     * mysql存储引擎 myisam 关注性能，不支持事务，表锁，不支持主外键；innodb 关注事务，支持事务，行锁，支持主外键
     */


}
