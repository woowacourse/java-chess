package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class YTest {

    @Test
    void of() {
        assertThat(Y.of(1)).isEqualTo(Y.ONE);
        assertThat(Y.of(2)).isEqualTo(Y.TWO);
    }

    @Test
    void of_잘못된_사용() {
        assertThatThrownBy(() -> Y.of(0)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void canIncrease_증가할_수_있을때() {
        assertThat(Y.ONE.canIncrease(7)).isTrue();
        assertThat(Y.TWO.canIncrease(-1)).isTrue();
    }

    @Test
    void canIncrease_증가할_수_없을때() {
        assertThat(Y.TWO.canIncrease(7)).isFalse();
        assertThat(Y.TWO.canIncrease(-2)).isFalse();
    }

    @Test
    void increase_증가할_수_있을때() {
        assertThat(Y.TWO.increase(3)).isEqualTo(Y.FIVE);
    }

    @Test
    void increase_증가할_수_없을때() {
        assertThatThrownBy(() -> Y.TWO.increase(-3)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void canDecrease_감소할_수_있을때() {
        assertThat(Y.ONE.canDecrease(-6)).isTrue();
        assertThat(Y.TWO.canDecrease(1)).isTrue();
    }

    @Test
    void canDecrease_감소할_수_없을때() {
        assertThat(Y.TWO.canDecrease(7)).isFalse();
        assertThat(Y.TWO.canDecrease(-7)).isFalse();
    }

    @Test
    void decrease_감소할_수_있을때() {
        assertThat(Y.EIGHT.decrease(3)).isEqualTo(Y.FIVE);
    }

    @Test
    void decrease_감소할_수_없을때() {
        assertThatThrownBy(() -> Y.TWO.decrease(-9)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void calculateDistance() {
        assertThat(Y.ONE.calculateDistance(Y.EIGHT)).isEqualTo(7);
        assertThat(Y.ONE.calculateDistance(Y.ONE)).isEqualTo(0);
    }

    @Test
    void isLargerThan() {
        assertThat(Y.ONE.isLargerThan(Y.ONE)).isFalse();
        assertThat(Y.EIGHT.isLargerThan(Y.SEVEN)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("getPathTestCase")
    void getPathFromTo(Y from, Y to, List<Y> expected) {
        assertThat(Y.getPathFromTo(from, to)).isEqualTo(expected);
    }

    private static Stream<Arguments> getPathTestCase() {
        return Stream.of(
            Arguments.of(Y.ONE, Y.EIGHT,
                Arrays.asList(Y.ONE, Y.TWO, Y.THREE, Y.FOUR, Y.FIVE, Y.SIX, Y.SEVEN, Y.EIGHT)),
            Arguments.of(Y.ONE, Y.ONE, Collections.singletonList(Y.ONE))
        );
    }
}
