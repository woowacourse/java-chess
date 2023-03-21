package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.PositionFixture.B4;
import static chess.PositionFixture.B7;
import static chess.PositionFixture.C4;
import static chess.PositionFixture.C6;
import static chess.PositionFixture.D4;
import static chess.PositionFixture.D5;
import static chess.PositionFixture.E2;
import static chess.PositionFixture.E3;
import static chess.PositionFixture.E4;
import static chess.PositionFixture.F3;
import static chess.PositionFixture.F5;
import static chess.PositionFixture.G2;
import static chess.PositionFixture.H1;
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
    @MethodSource("provideOtherPositionAndExpectedPassingPositions")
    @DisplayName("입력 받은 위치로 가는 경로에 있는 위치들을 반환한다")
    void find_passing_position_test(final Position otherPosition, final List<Position> expectedPassingPositions) {
        final List<Position> actualPassingPositions = E4.findPassingPositions(otherPosition);

        assertThat(actualPassingPositions).containsAll(expectedPassingPositions);
    }

    private static Stream<Arguments> provideOtherPositionAndExpectedPassingPositions() {
        return Stream.of(
                Arguments.of(B4, List.of(D4, C4)),
                Arguments.of(E2, List.of(E3)),
                Arguments.of(F5, List.of()),
                Arguments.of(H1, List.of(F3, G2)),
                Arguments.of(B7, List.of(D5, C6))
        );
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
