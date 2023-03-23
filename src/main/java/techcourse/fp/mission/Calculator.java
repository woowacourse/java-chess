package techcourse.fp.mission;

import java.util.List;

public class Calculator {

    public static int sumAll(List<Integer> numbers) {
//        int total = 0;
//        for (int number : numbers) {
//            total += number;
//        }
//        return total;
        return sumIfCan(numbers, number -> true);
    }

    public static int sumAllEven(List<Integer> numbers) {
//        int total = 0;
//        for (int number : numbers) {
//            if (number % 2 == 0) {
//                total += number;
//            }
//        }
//        return total;
        return sumIfCan(numbers, number -> number % 2 == 0);
    }

    public static int sumAllOverThree(List<Integer> numbers) {
        return sumIfCan(numbers, number -> number > 3);
    }

    public static int sumIfCan(List<Integer> numbers, Conditional conditional) {
        int total = 0;
        for (int number : numbers) {
            if (conditional.canSum(number)) {
                total += number;
            }
        }
        return total;
    }
}
