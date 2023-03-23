package techcourse.fp.mission;

import java.util.List;

public class Calculator {

    public interface Conditional {
        boolean test(Integer number);
    }

    public static int sumAllByCondition(List<Integer> numbers, Conditional conditional) {
        int total = 0;
        for (int number : numbers) {
            if (conditional.test(number)) {
                total += number;
            }
        }
        return total;
    }

    public static int sumAll(List<Integer> numbers) {
        return sumAllByCondition(numbers, number -> true);
    }

    public static int sumAllEven(List<Integer> numbers) {
        return sumAllByCondition(numbers, number -> number % 2 == 0);
    }

    public static int sumAllOverThree(List<Integer> numbers) {
        return sumAllByCondition(numbers, number -> number > 3);
    }
}
