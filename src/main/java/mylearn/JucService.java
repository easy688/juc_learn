package mylearn;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.ReentrantLock;

public class JucService {
    public static void main(String[] args) throws Exception{
        Class<?> clazz = Unsafe.class;
        Field f = clazz.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(clazz);
        //手动加内存屏障
        unsafe.storeFence();
    }
}
