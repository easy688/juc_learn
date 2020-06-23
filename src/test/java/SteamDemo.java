import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SteamDemo {
    public static void main(String[] args) {
        User user = new User(11, 23, "a");
        User user1 = new User(12, 24, "b");
        User user2 = new User(13, 25, "c");
        User user3 = new User(14, 26, "d");
        User user4 = new User(15, 27, "e");
        //只有一行代码可以不写括号和return
        List<User> list = Arrays.asList(user, user1, user2, user3, user4);
        list.stream().filter(t -> t.getId() % 2 == 0 && t.getAge() > 24).
                map(u -> u.getName().toUpperCase()).
                sorted((s1, s2) -> {
                    return s1.compareTo(s2);
                }).limit(1).forEach(System.out::println);


        //函数型接口，一个输入一个返回
        Function<String, Integer> function = s -> {
            return s.length();
        };
        System.out.println(function.apply("abc"));
        //断定型接口,一个输入返回是boolean型
        Predicate<String> predicate = s -> {
            return s.isEmpty();
        };
        System.out.println(predicate.test("zbc"));
        //消费型接口，输入一个参数，没有返回值
        Consumer<String> consumer = s -> {
            System.out.println(s);
        };
        consumer.accept("abc");
        //供给型函数，没有输入参数，有返回
        Supplier<String> supplier = () -> {
            return "好的";
        };
        System.out.println(supplier.get());

    }
}
