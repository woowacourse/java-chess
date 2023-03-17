package techcourse.fp.mission;

import java.util.List;

public class Calculator {
    public static int sum(Conditional conditional, List<Integer> numbers) {
        int total = 0;
        for (Integer number : numbers) {
            if (conditional.test(number)) {
                total += number;
            }
        }
        return total;
    }
}
