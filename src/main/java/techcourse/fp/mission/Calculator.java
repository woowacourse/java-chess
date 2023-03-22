package techcourse.fp.mission;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.function.Predicate;

public class Calculator {

    public static int sumAll(List<Integer> numbers) {
        return sum(numbers, number -> {
            return true;
        });
    }

    public static int sumAllEven(List<Integer> numbers) {
        return sum(numbers, number -> {
            return number % 2 == 0;
        });
    }

    public static int sumAllOverThree(List<Integer> numbers) {
        return sum(numbers, number -> {
            return number > 3;
        });
    }

    public static int sum(List<Integer> numbers, Conditional condition) {
        int total = 0;
        for (int number : numbers) {
            if (condition.test(number)) {
                total += number;
            }
        }
        return total;
    }
}
