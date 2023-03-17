package techcourse.fp.mission;

import java.util.List;

/**
 * techcourse.fp 하위 클래스는
 * 우아한테크코스 미션입니다.
 * 코드리뷰는 안해주셔도 됩니다!
 */
public class Calculator {

    public static int sumAll(List<Integer> numbers) {
        return sumAll(numbers, (num) -> true);
    }

    public static int sumAllEven(List<Integer> numbers) {
        return sumAll(numbers, (num) -> num % 2 == 0);
    }

    public static int sumAllOverThree(List<Integer> numbers) {
        //TODO: List에 담긴 값 중 3보다 큰 수만을 더해야 한다.
        return sumAll(numbers, (num) -> num > 3);
    }

    private static int sumAll(List<Integer> numbers, Conditional conditional) {
        return numbers.stream()
                .mapToInt(i -> i)
                .filter(conditional::condition)
                .sum();
    }
}

interface Conditional {
    boolean condition(Integer number);
}
