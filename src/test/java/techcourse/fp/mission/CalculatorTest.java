package techcourse.fp.mission;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
}
