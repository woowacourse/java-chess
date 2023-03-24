package techcourse.fp.mission;

import java.util.List;

public class Calculator {

    public static int sumAll(List<Integer> numbers) {
        return sumAll(numbers, number -> true);
    }

    public static int sumAllEven(List<Integer> numbers) {
        return sumAll(numbers, number -> (number % 2) == 0);
    }

    public static int sumAllOverThree(List<Integer> numbers) {
        return sumAll(numbers, number -> number > 3);
    }

    public static int sumAll(List<Integer> numbers, Conditional conditional) {
        int sum = 0;
        for (Integer number : numbers) {
            if (conditional.test(number)) {
                sum += number;
            }
        }
        return sum;
    }

    public interface Conditional {

        boolean test(Integer number);
    }
}
