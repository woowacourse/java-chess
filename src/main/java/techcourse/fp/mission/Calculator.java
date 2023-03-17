package techcourse.fp.mission;

import java.util.List;
import java.util.function.Predicate;

public class Calculator {

    public static int sumByCondition(List<Integer> numbers, Predicate<Integer> condition) {
        int total = 0;
        for (int number : numbers) {
            if (condition.test(number)) {
                total += number;
            }
        }
        return total;
    }
}
