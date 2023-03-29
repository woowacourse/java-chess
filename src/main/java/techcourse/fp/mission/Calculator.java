package techcourse.fp.mission;

import java.util.List;
import java.util.function.Predicate;

public class Calculator {

    public static int sumAll(final List<Integer> numbers) {
        return sum(numbers, number -> number == number);
    }

    public static int sumAllEven(final List<Integer> numbers) {
        return sum(numbers, number -> number % 2 == 0);
    }

    public static int sumAllOverThree(final List<Integer> numbers) {
        return sum(numbers, number -> number > 3);
    }

    public static int sum(List<Integer> numbers, Predicate<Integer> conditional) {
        // conditional.test(number)를 활용해 구현할 수 있다.
        int total = 0;
        for (int number : numbers) {
            if (conditional.test(number)) {
                total += number;
            }
        }
        return total;
    }

}
