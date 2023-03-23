package techcourse.fp.mission;

import java.util.List;
import java.util.function.Predicate;

public class Calculator {

    public static int sumAll(List<Integer> numbers) {
        return sumBasic(numbers, ignored -> true);
    }

    public static int sumAllEven(List<Integer> numbers) {
        return sumBasic(numbers, s -> s % 2 == 0);
    }

    public static int sumAllOverThree(List<Integer> numbers) {
        return sumBasic(numbers, s -> s > 3);
    }

    public static int sumBasic(List<Integer> numbers, Predicate<Integer> predicate) {
        int total = 0;
        for (int number : numbers) {
            if (predicate.test(number)) {
                total += number;
            }
        }
        return total;
    }

}
