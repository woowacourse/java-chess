package techcourse.fp.mission;

import java.util.List;
<<<<<<< HEAD
import java.util.function.IntPredicate;
import java.util.function.Predicate;
=======
import java.util.function.Function;
>>>>>>> 5007451f2bd69138d02baa596277b3c794899817

public class Calculator {

    public static int sumWhen(List<Integer> numbers, Function<Integer, Boolean> sumCondition) {
        int total = 0;
        for (int number : numbers) {
            if (sumCondition.apply(number)) total += number;
        }
        return total;
    }

    public static int sumAll(List<Integer> numbers) {
        return sumWhen(numbers, num -> (true));
    }

    public static int sumAllEven(List<Integer> numbers) {
        return sumWhen(numbers, num -> (num % 2 == 0));
    }

    public static int sumAllOverThree(List<Integer> numbers) {
<<<<<<< HEAD
        int total = 0;

        //TODO: List에 담긴 값 중 3보다 큰 수만을 더해야 한다.
        IntPredicate overThree = (number) -> number > 3;
        for(int number : numbers){
            if(overThree.test(number)){
                total += number;
            }
        }
        return total;
=======
        return sumWhen(numbers, num -> (num > 3));
>>>>>>> 5007451f2bd69138d02baa596277b3c794899817
    }
}
