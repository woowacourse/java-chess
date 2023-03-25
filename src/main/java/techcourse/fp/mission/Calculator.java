package techcourse.fp.mission;

import java.util.List;
import java.util.function.IntPredicate;

public class Calculator {
    public static int sumAll(List<Integer> numbers, Conditional condition) {
        return calculateTotal(numbers, condition);
    }

    public static int sumAllEven(List<Integer> numbers, Conditional condition) {
        return calculateTotal(numbers, condition);
    }

    public static int sumAllOverThree(List<Integer> numbers, Conditional condition) {
        //TODO: List에 담긴 값 중 3보다 큰 수만을 더해야 한다.
//        int total = 0;
//        for (int number : numbers) {
//            if (conditional.filter(number)) {
//                total += number;
//            }
//        }
//
//        return total;
        return calculateTotal(numbers, condition);
    }

    private static int calculateTotal(List<Integer> numbers, Conditional condition) {
        int total = 0;
        for (int number : numbers) {
            if (condition.filter(number)) {
                total += number;
            }
        }
        return total;
    }
}
