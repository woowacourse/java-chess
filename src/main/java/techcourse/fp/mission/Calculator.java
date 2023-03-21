package techcourse.fp.mission;

import java.util.List;

public class Calculator {

    public static int sumAll(List<Integer> numbers) {
        return sum(numbers, ignored -> true);

    }

    public static int sumAllEven(List<Integer> numbers) {
        return sum(numbers, number -> number % 2 == 0);
    }

    public static int sumAllOverThree(List<Integer> numbers) {
        return sum(numbers, number -> number > 3);
    }

    public static int sum(List<Integer> numbers, Condition condition) {
        int total = 0;
        for (Integer number : numbers) {
            if (condition.filter(number)) {
                total += number;
            }
        }
        return total;
    }
}
