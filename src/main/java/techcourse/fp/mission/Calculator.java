package techcourse.fp.mission;

import java.util.List;

public class Calculator {

    public static int sumConditionalStrategy(List<Integer> numbers, CalculateStrategy strategy) {
        return calculateTotal(numbers, strategy);
    }

    private static int calculateTotal(final List<Integer> numbers, final CalculateStrategy strategy) {
        int total = 0;

        for (final int number : numbers) {
            if (strategy.canCalculate(number)) {
                total += number;
            }
        }
        return total;
    }

    @FunctionalInterface
    interface CalculateStrategy {

        boolean canCalculate(Integer number);
    }
}
