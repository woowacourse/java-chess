package techcourse.fp.mission;

import java.util.List;

public class Calculator {

    public static int sumAll(List<Integer> numbers, Condition condition) {
        int total = 0;
        for (int number : numbers) {
            if (condition.test(number)) {
                total += number;
            }
        }
        return total;
    }

    public static int sumAllEven(List<Integer> numbers) {
        return sumAll(numbers, i -> i % 2 == 0);
    }

    public static int sumAllOverThree(List<Integer> numbers) {
        return sumAll(numbers, i -> i > 3);
    }
}
