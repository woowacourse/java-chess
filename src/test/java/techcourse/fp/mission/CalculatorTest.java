package techcourse.fp.mission;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    private final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

    @Test
    void sumAll() {
        int sum = Calculator.sumAllByCondition(numbers, value -> true);
        assertThat(sum).isEqualTo(21);
    }

    @Test
    void sumAllEven() {
        int sum = Calculator.sumAllByCondition(numbers, value -> value % 2 == 0);
        assertThat(sum).isEqualTo(12);
    }

    @Test
    void sumAllOverThree() {
        int sum = Calculator.sumAllByCondition(numbers, number -> number > 3);
        assertThat(sum).isEqualTo(15);
    }
}
