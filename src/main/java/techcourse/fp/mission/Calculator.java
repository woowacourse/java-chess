package techcourse.fp.mission;

import java.util.List;
import java.util.function.Predicate;

public class Calculator {

    public static int sumAll(List<Integer> numbers) {
        return sumByPredicate(numbers, number -> true);
    }

    public static int sumAllEven(List<Integer> numbers) {
        return sumByPredicate(numbers, number -> number % 2 == 0);
    }

    public static int sumAllOverThree(List<Integer> numbers) {
        return sumByPredicate(numbers, number -> number > 3);
    }

    private static int sumByPredicate(final List<Integer> numbers, Predicate<Integer> predicate) {
        int total = 0;

        for (int number : numbers) {
            if (predicate.test(number)) {
                total += number;
            }
        }

        return total;
    }
}
