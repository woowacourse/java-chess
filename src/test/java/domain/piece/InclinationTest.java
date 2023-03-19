package domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class InclinationTest {

    @Test
    @DisplayName("기울기가 양의 무한대인 경우를 비교할 수 있다")
    void isSameAsPositiveInfinity() {
        Inclination expected = Inclination.POSITIVE_INFINITY;
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(2, 0);

        double actual = start.getInclination(end);

        assertThat(Inclination.of(actual)).isEqualTo(expected);
    }

    @Test
    @DisplayName("기울기가 음의 무한대인 경우를 비교할 수 있다")
    void isSameAsNegativeInfinity() {
        Inclination expected = Inclination.NEGATIVE_INFINITY;
        Coordinate start = new Coordinate(2, 0);
        Coordinate end = new Coordinate(0, 0);

        double actual = start.getInclination(end);

        assertThat(Inclination.of(actual)).isEqualTo(expected);

    }

    @Test
    @DisplayName("기울기가 +0인 경우를 비교할 수 있다")
    void isSameAsPositiveZero() {
        Inclination expected = Inclination.ZERO;
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(0, 1);

        double actual = start.getInclination(end);

        assertThat(Inclination.of(actual)).isEqualTo(expected);

    }

    @Test
    @DisplayName("기울기가 -0인 경우를 비교할 수 있다")
    void isSameAsNegativeZero() {
        Inclination expected = Inclination.MINUS_ZERO;
        Coordinate start = new Coordinate(0, 1);
        Coordinate end = new Coordinate(0, 0);

        double actual = start.getInclination(end);

        assertThat(Inclination.of(actual)).isEqualTo(expected);

    }

    @Test
    @DisplayName("기울기가 0이 아닌 실수인 경우를 비교할 수 있다")
    void isSameAsPositiveZeroPointFive() {
        Inclination expected = Inclination.ZERO_POINT_FIVE;
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(1, 2);

        double actual = start.getInclination(end);

        assertThat(Inclination.of(actual)).isEqualTo(expected);
    }
}
