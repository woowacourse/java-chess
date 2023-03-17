package techcourse.fp.mission;

import java.util.List;
import java.util.function.Predicate;

public class Calculator {

    public static int sumAll(List<Integer> numbers) {
        /*
        int total = 0;
        for (int number : numbers) {
            total += number;
        }
        return total;

         */
        return sum(numbers, ignored -> true);
    }

    public static int sumAllEven(List<Integer> numbers) {
        /*
        int total = 0;
        for (int number : numbers) {
            if (number % 2 == 0) {
                total += number;
            }
        }
        return total;

         */
        return sum(numbers, number -> number % 2 == 0);
    }

    public static int sumAllOverThree(List<Integer> numbers) {
        /*
        int total = 0;

        //TODO: List에 담긴 값 중 3보다 큰 수만을 더해야 한다.
        for (int number : numbers) {
            if (number > 3) {
                total += number;
            }
        }

        return total;

         */
        return sum(numbers, number -> number > 3);
    }

    public static int sumAllPredicate(List<Integer> numbers, Predicate<Integer> filter) {
        int total = 0;

        for (int number : numbers) {
            if (filter.test(number)) {
                total += number;
            }
        }

        return total;
    }

    private static int sum(List<Integer> numbers, Predicate<Integer> filter) {
        int total = 0;

        for (int number : numbers) {
            if (filter.test(number)) {
                total += number;
            }
        }

        return total;
    }
}
