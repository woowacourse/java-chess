package techcourse.fp.mission;

import java.util.List;

public class Calculator {

    public interface Conditional {
        boolean test(Integer number);
    }

    public static int sumAll(List<Integer> numbers, Conditional conditional) {
        int total = 0;
        for (int number : numbers) {
            if (conditional.test(number)) {
                total += number;
            }
        }
        return total;
    }

    public static int sumAllEven(List<Integer> numbers) {
        return sumAll(numbers, new Conditional() {
            @Override
            public boolean test(Integer number) {
                return number % 2 == 0;
            }
        });
    }

    public static int sumAllOverThree(List<Integer> numbers) {
        int total = 0;

        //TODO: List에 담긴 값 중 3보다 큰 수만을 더해야 한다.

        return sumAll(numbers, new Conditional() {
            @Override
            public boolean test(Integer number) {
                return number > 3;
            }
        });
    }
}
