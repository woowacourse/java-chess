package techcourse.fp.mission;

import java.util.List;

public class Calculator {

    public static int sumAll(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(x -> x)
                .sum();
    }

    public static int sumAll(List<Integer> numbers, Conditional conditional) {
        return numbers.stream()
                .filter(x -> conditional.test(x))
                .mapToInt(x -> x)
                .sum();
    }

    public static int sumAllEven(List<Integer> numbers) {
        return numbers.stream()
                .filter(x -> x % 2 == 0)
                .mapToInt(x -> x)
                .sum();

    }

    public static int sumAllOverThree(List<Integer> numbers) {
        return numbers.stream()
                .filter(x -> x > 3)
                .mapToInt(x -> x)
                .sum();
    }
}
