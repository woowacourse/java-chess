package techcourse.fp.mission;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class CalculatorTest {

    private final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

    @Test
    public void sumAll() {
        int sum = Calculator.sumAll(numbers);
        assertThat(sum).isEqualTo(21);
    }

    @Test
    public void sumAllEven() {
        int sum = Calculator.sumAllEven(numbers);
        assertThat(sum).isEqualTo(12);
    }

    @Test
    public void sumAllOverThree() {
        int sum = Calculator.sumAllOverThree(numbers);
        assertThat(sum).isEqualTo(15);
    }

    @Test
    public void sumAllBySumStrategy() {
        int sum = Calculator.sumAll(numbers, (number) -> true);
        assertThat(sum).isEqualTo(21);
    }

    @Test
    public void sumAllEvenBySumStrategy() {
        int sum = Calculator.sumAll(numbers, (number) -> number % 2 == 0);
        assertThat(sum).isEqualTo(12);
    }

    @Test
    public void sumAllOverThreeBySumStrategy() {
        int sum = Calculator.sumAll(numbers, (number) -> number > 3);
        assertThat(sum).isEqualTo(15);
    }
}
