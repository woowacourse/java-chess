package techcourse.fp.mission;

import java.util.List;

public class Calculator {

    public static int sumAll(List<Integer> numbers) {

        return sum(numbers, number -> true);

//        int total = 0;
//        for (int number : numbers) {
//            if (true) {
//                total += number;
//            }
//        }
//        return total;
    }

    public static int sumAllEven(List<Integer> numbers) {
        return sum(numbers, number -> number % 2 == 0);
//        int total = 0;
//        for (int number : numbers) {
//            if (number % 2 == 0) {
//                total += number;
//            }
//        }
//        return total;
    }

    public static int sumAllOverThree(List<Integer> numbers) {
        return sum(numbers, number -> number > 3);

//        int total = 0;
//
//        for (int number : numbers) {
//            if (number > 3) {
//                total += number;
//            }
//        }
//
//        return total;
    }

    public static int sum(List<Integer> numbers, Conditional conditional) {
        int total = 0;
        for (int number : numbers) {
            if (conditional.test(number)) {
                total += number;
            }
        }
        return total;
    }
}
