package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.PositionFixture.D5;
import static chess.PositionFixture.E4;
import static chess.domain.position.File.A;
import static chess.domain.position.File.D;
import static chess.domain.position.Rank.ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class PositionTest {

    @Test
    @DisplayName("File과 Rank정보로 위치를 만든다")
    void init_test() {
        assertThatNoException().isThrownBy(() -> Position.of(A, ONE));
    }

    @ParameterizedTest
    @CsvSource({"A, FOUR, true", "E, ONE, true", "C, SIX, false", "G, TWO, false"})
    @DisplayName("상하좌우 위치에 있는지 확인한다")
    void cross_position_check_test(final File file, final Rank rank, final boolean expected) {
        final Position otherPosition = Position.of(file, rank);

        final boolean actual = E4.isInCrossPosition(otherPosition);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"A, FOUR, false", "E, ONE, false", "C, SIX, true", "G, TWO, true"})
    @DisplayName("대각선 위치에 있는지 확인한다")
    void diagonal_position_check_test(final File file, final Rank rank, final boolean expected) {
        final Position otherPosition = Position.of(file, rank);

        final boolean actual = E4.isInDiagonalPosition(otherPosition);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"D, FOUR, 1", "F, FIVE, 2", "G, FIVE, 3"})
    @DisplayName("맨허튼 거리를 계산한다")
    void manhattan_distance_calculate_test(final File file, final Rank rank, final int expectedDistance) {
        final Position otherPosition = Position.of(file, rank);

        final int actualDistance = E4.calculateManhattanDistance(otherPosition);

        assertThat(actualDistance).isEqualTo(expectedDistance);
    }

    @ParameterizedTest
    @CsvSource({"FOUR, true", "SIX, false"})
    @DisplayName("Rank가 높은지 확인한다.")
    void upper_rank_check_test(final Rank otherRank, final boolean expected) {
        final Position position = D5;
        final Position otherPosition = Position.of(D, otherRank);

        final boolean actual = position.isUpperRankThan(otherPosition);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"FOUR, false", "SIX, true"})
    @DisplayName("Rank가 낮은지 확인한다.")
    void lower_rank_check_test(final Rank otherRank, final boolean expected) {
        final Position position = D5;
        final Position otherPosition = Position.of(D, otherRank);

        final boolean actual = position.isLowerRankThan(otherPosition);

        assertThat(actual).isEqualTo(expected);
    }
}
