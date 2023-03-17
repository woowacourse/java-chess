package techcourse.fp.mission;

import java.util.List;
import java.util.function.IntPredicate;

public class Calculator {

    public static int sumAll(List<Integer> numbers) {
        return sumAll(numbers, ignore -> true);
    }

    public static int sumAllEven(List<Integer> numbers) {
        return sumAll(numbers, number -> (number & 1) == 0);
    }

    public static int sumAllOverThree(List<Integer> numbers) {
        return sumAll(numbers, number -> number > 3);
    }

    private static int sumAll(List<Integer> numbers, IntPredicate condition) {
        int total = 0;
        for (Integer number : numbers) {
            if (condition.test(number)) {
                total += number;
            }
        }
        return total;
    }
}
