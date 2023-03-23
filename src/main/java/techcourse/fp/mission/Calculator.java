package techcourse.fp.mission;

import java.util.List;
import java.util.function.IntPredicate;

public class Calculator {
    public static int sumAllByCondition(List<Integer> numbers, IntPredicate condition) {
        int total = 0;
        
        //TODO: List에 담긴 값 중 3보다 큰 수만을 더해야 한다.
        for (int number : numbers) {
            if (condition.test(number)) {
                total += number;
            }
        }
        
        return total;
    }
}
