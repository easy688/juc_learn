import java.util.ArrayList;

@FunctionalInterface
public interface LambDemo {
    void add(int x, int y);

    default int mu(int x, int y) {
        return x * y;
    }

    default int mu2(int x, int y) {
        return x * y;
    }

    static int mu3(int x, int y) {
        return x - y;
    }

    static int mu4(int x, int y) {
        return x - y;
    }

    public static void main(String[] args) {
        //lamb表达式 拷贝小括号，写死右箭头，落地大括号
        //函数式接口中只能有一个方法,可以有多个默认方法和多个静态方法
        LambDemo lambDemo = (int x, int y) -> {
            System.out.println("lamb表达式");
        };
        lambDemo.add(5, 6);

    }
}
