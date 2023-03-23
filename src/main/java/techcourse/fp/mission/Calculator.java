package techcourse.fp.mission;

import java.util.List;
import java.util.function.Predicate;

public class Calculator {

    public static int sum(List<Integer> numbers, final Predicate<Integer> condition) {
        return sumAll(numbers, condition);
    }

    public static int sumAll(List<Integer> numbers, Predicate<Integer> condition) {
        int total = 0;
        for (Integer number : numbers) {
            if (condition.test(number)) {
                total += number;
            }
        }
        return total;
    }
}
