package techcourse.fp.study;

import java.util.List;
import java.util.function.Consumer;

public class PlayGround {

    public static void printAllOld(List<Integer> numbers) {
        System.out.println("printAllOld");

        for (int number : numbers) {
            System.out.println(number);
        }
    }

    public static void printAllAnonymous(List<Integer> numbers) {
        System.out.println("printAllAnonymous");

        numbers.forEach(System.out::println);

        numbers.forEach(new Consumer<Integer>() {
            public void accept(Integer value) {
                System.out.println(value);
            }
        });
    }

    public static void printAllLambda(List<Integer> numbers) {
        System.out.println("printAllAnonymous");

        numbers.forEach(value -> System.out.println(value));
    }

    public static void printAllMethodReference(List<Integer> numbers) {
        System.out.println("printAllLambda");

        numbers.forEach(System.out::println);
    }
}
