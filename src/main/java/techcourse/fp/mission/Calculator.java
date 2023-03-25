package techcourse.fp.mission;

import java.util.List;

public class Calculator {

    public static int sumAll(List<Integer> numbers) {
        return sumAll(numbers, number -> true);
    }

    public static int sumAll(List<Integer> numbers, Conditional<Integer> conditional) {
        int total = 0;
        for (int number : numbers) {
            total += testNumber(number, conditional);
        }
        return total;
    }

    private static int testNumber(int number, Conditional<Integer> conditional) {
        if (conditional.test(number)) {
            return number;
        }
        return 0;
    }

    public static int sumAllEven(List<Integer> numbers) {
        return sumAll(numbers, number -> number % 2 == 0);
    }

    public static int sumAllOverThree(List<Integer> numbers) {
        return sumAll(numbers, number -> number > 3);
    }
}
