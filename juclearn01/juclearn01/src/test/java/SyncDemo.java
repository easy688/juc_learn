public class SyncDemo {

    public static synchronized void sendSms(){
        System.out.println("send sms:发邮件");
    }
    public static synchronized void sendEms(){
        System.out.println("send ems:发短信");
    }
    public  synchronized void sendSms1(){
        System.out.println("send sms:发邮件1");
    }
    public  synchronized void sendEms1(){
        System.out.println("send ems:发短信1");
    }
    public void call(){
        System.out.println("打电话");
    }

    /**
     * 非静态同步方法锁的是当前实例
     * 静态同步方法锁的是当前类
     * 非同步方法不竞争锁
     * 持有锁不同互不影响
     * @param args
     */
    public static void main(String[] args) {
        SyncDemo s=new SyncDemo();
        new Thread(()->{
            s.sendEms1();

               },"AAA").start();
        new Thread(()->{
            s.sendSms1();
        },"BBB").start();


    }
}
