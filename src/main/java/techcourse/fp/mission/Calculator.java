package techcourse.fp.mission;

import java.util.List;

public class Calculator {

    public static int sumAll(List<Integer> numbers, Conditional conditional) {
        int total = 0;
        for (int number : numbers) {
            total += number;
        }
        return total;
    }

    public static int sumAllEven(List<Integer> numbers, Conditional conditional) {
        int total = 0;
        for (int number : numbers) {
            if (conditional.canAdd(number)) {
                total += number;
            }
        }
        return total;
    }

    public static int sumAllOverThree(List<Integer> numbers, Conditional conditional) {
        int total = 0;

        for (int number : numbers) {
            if (conditional.canAdd(number)) {
                total += number;
            }
        }
        return total;
    }
}
