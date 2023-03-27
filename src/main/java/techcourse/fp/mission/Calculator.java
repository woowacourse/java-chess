package techcourse.fp.mission;

import java.util.List;
import java.util.function.Predicate;

public class Calculator {

    public static int sumAll(List<Integer> numbers) {
        Predicate<Integer> sumCondition = (number) -> true;
        return initSetting(numbers, sumCondition);
    }

    public static int sumAllEven(List<Integer> numbers) {
        Predicate<Integer> sumCondition = (number) -> number % 2 == 0;
        return initSetting(numbers, sumCondition);
    }

    public static int sumAllOverThree(List<Integer> numbers) {
        Predicate<Integer> sumCondition = (number) -> number > 3;
        return initSetting(numbers, sumCondition);
    }

    //integer 받으면 Boolean return
    //어떤 기준으로 판단할건지 기준 명시해줘야함
    // ex. Predicate<Integer> sumCondition = (number) -> number % 2 == 0;
    public static int initSetting(List<Integer> numbers, Predicate<Integer> sumCondition) {
        int sum = 0;
        for (Integer number : numbers) {
            if (sumCondition.test(number)) {
                sum += number;
            }
        }
        return sum;
    }
}
