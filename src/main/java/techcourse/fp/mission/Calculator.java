package techcourse.fp.mission;

import java.util.List;

public class Calculator {

    public static int sumAll(List<Integer> numbers) {
        int total = 0;
        for (int number : numbers) {
            total += number;
        }
        return total;
    }

    public static int sumAllEven(List<Integer> numbers) {
        int total = 0;
        for (int number : numbers) {
            if (number % 2 == 0) {
                total += number;
            }
        }
        return total;
    }

    public static int sumAllOverThree(List<Integer> numbers, Conditional conditional) {
        int total = 0;

        for (Integer number : numbers) {
            if (conditional.test(number)) {
                total += number;
            }
        }
        return total;
    }
}
