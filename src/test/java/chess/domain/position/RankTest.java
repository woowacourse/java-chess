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

public class RankTest {

    @Test
    void of() {
        assertThat(Rank.of(1)).isEqualTo(Rank.ONE);
        assertThat(Rank.of(2)).isEqualTo(Rank.TWO);
    }

    @Test
    void of_잘못된_사용() {
        assertThatThrownBy(() -> Rank.of(0)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void canIncrease_증가할_수_있을때() {
        assertThat(Rank.ONE.canIncrease(7)).isTrue();
        assertThat(Rank.TWO.canIncrease(-1)).isTrue();
    }

    @Test
    void canIncrease_증가할_수_없을때() {
        assertThat(Rank.TWO.canIncrease(7)).isFalse();
        assertThat(Rank.TWO.canIncrease(-2)).isFalse();
    }

    @Test
    void increase_증가할_수_있을때() {
        assertThat(Rank.TWO.increase(3)).isEqualTo(Rank.FIVE);
    }

    @Test
    void increase_증가할_수_없을때() {
        assertThatThrownBy(() -> Rank.TWO.increase(-3))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void canDecrease_감소할_수_있을때() {
        assertThat(Rank.ONE.canDecrease(-6)).isTrue();
        assertThat(Rank.TWO.canDecrease(1)).isTrue();
    }

    @Test
    void canDecrease_감소할_수_없을때() {
        assertThat(Rank.TWO.canDecrease(7)).isFalse();
        assertThat(Rank.TWO.canDecrease(-7)).isFalse();
    }

    @Test
    void decrease_감소할_수_있을때() {
        assertThat(Rank.EIGHT.decrease(3)).isEqualTo(Rank.FIVE);
    }

    @Test
    void decrease_감소할_수_없을때() {
        assertThatThrownBy(() -> Rank.TWO.decrease(-9))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void calculateDistance() {
        assertThat(Rank.ONE.calculateDistance(Rank.EIGHT)).isEqualTo(7);
        assertThat(Rank.ONE.calculateDistance(Rank.ONE)).isEqualTo(0);
    }

    @Test
    void isLargerThan() {
        assertThat(Rank.ONE.isLargerThan(Rank.ONE)).isFalse();
        assertThat(Rank.EIGHT.isLargerThan(Rank.SEVEN)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("getPathTestCase")
    void getPathFromTo(Rank from, Rank to, List<Rank> expected) {
        assertThat(Rank.getPathFromTo(from, to)).isEqualTo(expected);
    }

    private static Stream<Arguments> getPathTestCase() {
        return Stream.of(
            Arguments.of(Rank.ONE, Rank.EIGHT,
                Arrays.asList(Rank.ONE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX,
                    Rank.SEVEN, Rank.EIGHT)),
            Arguments.of(Rank.ONE, Rank.ONE, Collections.singletonList(Rank.ONE))
        );
    }
}
