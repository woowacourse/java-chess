package study;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ZeroTypeTest {

    @Test
    void floatingZero1() {
        double val = 0.0;
        int val2 = 0;

        assertThat(val == val2).isTrue();
    }

    @Test
    void floatingZero2() {
        double val = 1.0;
        int val2 = 1;

        assertThat(val == val2).isTrue();
    }

    @Test
    void remainderZero() {
        double val = 1.0;

        assertThat(val % 1 == 0.0).isTrue();
        assertThat(val % 1 == 0).isTrue();
    }
}
