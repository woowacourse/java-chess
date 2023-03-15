package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.domain.File.A;
import static chess.domain.File.E;
import static chess.domain.Rank.FOUR;
import static chess.domain.Rank.ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class PositionTest {

    private final Position E_FOUR = new Position(E, FOUR);

    @Test
    @DisplayName("File과 Rank정보로 위치를 만든다")
    void initTest() {
        assertThatNoException().isThrownBy(() -> new Position(A, ONE));
    }

    @ParameterizedTest
    @CsvSource({"A, FOUR, true", "E, ONE, true", "C, SIX, false", "G, TWO, false"})
    @DisplayName("상하좌우 위치에 있는지 확인한다")
    void isInCrossPositionTest(final File file, final Rank rank, final boolean expected) {
        final Position otherPosition = new Position(file, rank);

        final boolean actual = E_FOUR.isInCrossPosition(otherPosition);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"A, FOUR, false", "E, ONE, false", "C, SIX, true", "G, TWO, true"})
    @DisplayName("대각선 위치에 있는지 확인한다")
    void isInDiagonalPositionTest(final File file, final Rank rank, final boolean expected) {
        final Position otherPosition = new Position(file, rank);

        final boolean actual = E_FOUR.isInDiagonalPosition(otherPosition);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"D, FOUR, true", "E, FIVE, true", "D, THREE, false", "F, FIVE, false"})
    @DisplayName("상하좌우로 한 칸 거리인지 확인한다")
    void isCrossNeighborPositionTest(final File file, final Rank rank, final boolean expected) {
        final Position otherPosition = new Position(file, rank);

        final boolean actual = E_FOUR.isCrossNeighborPosition(otherPosition);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"D, FOUR, false", "E, FIVE, false", "D, THREE, true", "F, FIVE, true"})
    @DisplayName("대각선로 한 칸 거리인지 확인한다")
    void isDiagonalNeighborPositionTest(final File file, final Rank rank, final boolean expected) {
        final Position otherPosition = new Position(file, rank);

        final boolean actual = E_FOUR.isDiagonalNeighborPosition(otherPosition);

        assertThat(actual).isEqualTo(expected);
    }
}
