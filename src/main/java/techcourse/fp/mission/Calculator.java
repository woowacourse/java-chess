package techcourse.fp.mission;

import java.util.List;

public class Calculator {

    public static int sumAll(final List<Integer> numbers) {
        return sumAll(numbers, (number) -> true);
    }

    public static int sumAllEven(final List<Integer> numbers) {
        int total = 0;
        for (final int number : numbers) {
            if (number % 2 == 0) {
                total += number;
            }
        }
        return total;
    }

    public static int sumAllOverThree(final List<Integer> numbers) {
        return sumAll(numbers, (number) -> number > 3);
    }

    private static int sumAll(final List<Integer> numbers, final Conditional conditional) {
        return numbers.stream()
                .mapToInt(i -> i)
                .filter(conditional::condition)
                .sum();
    }
}

@FunctionalInterface
interface Conditional {
    boolean condition(int number);
}
