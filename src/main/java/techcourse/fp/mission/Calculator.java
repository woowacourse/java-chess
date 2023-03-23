package techcourse.fp.mission;

import java.util.List;

public class Calculator {

    public static int sumAll(List<Integer> numbers, Conditional conditional) {
        int total = 0;
        for (int number : numbers) {
            if (conditional.test(number)) {
                total += number;
            }
        }
        return total;
    }

    public static int sumAllEven(List<Integer> numbers, Conditional conditional) {
        return sumAll(numbers, conditional);
    }

    public static int sumAllOverThree(List<Integer> numbers, final Conditional conditional) {
        return sumAll(numbers, conditional);
    }
}
