package techcourse.fp.mission;

import java.util.List;

public class Calculator {

    public static int sumAll(List<Integer> numbers, Conditional conditional) {
        int total = 0;
        for (int number : numbers) {
//            total += number;
            conditional.test(number);
        }
        return total;
    }

    public static int sumAllEven(List<Integer> numbers) {
        int total = 0;
        for (int number : numbers) {
            if (number % 2 == 0) {
                total += number;
            }
        }
        return total;
    }

    public static int sumAllOverThree(List<Integer> numbers) {
        int total = 0;

        //TODO: List에 담긴 값 중 3보다 큰 수만을 더해야 한다.
        for (int number : numbers) {
            if (number > 3) {
                total += number;
            }
        }
        return total;
    }

    public boolean test(Integer number) {
        Conditional conditional1 = number1 -> {
            return number1 % 2== 0;
        };

        Conditional conditional2 = number1 -> {
            return number1 > 3;
        };
        return false;
    }

}
