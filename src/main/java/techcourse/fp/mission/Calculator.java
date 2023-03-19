package techcourse.fp.mission;

import java.util.List;
import java.util.function.Predicate;

public class Calculator {

    public static int sumAll(List<Integer> numbers, Predicate<Integer> rule) {
        int total = 0;

        for (int number : numbers) {
            if (rule.test(number)) {
                total += number;
            }
        }
        return total;
    }
}
