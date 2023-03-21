package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.domain.position.File.A;
import static chess.domain.position.File.B;
import static chess.domain.position.File.C;
import static chess.domain.position.File.D;
import static chess.domain.position.File.E;
import static chess.domain.position.File.F;
import static chess.domain.position.File.G;
import static chess.domain.position.File.H;
import static chess.domain.position.Rank.FIVE;
import static chess.domain.position.Rank.FOUR;
import static chess.domain.position.Rank.ONE;
import static chess.domain.position.Rank.SEVEN;
import static chess.domain.position.Rank.SIX;
import static chess.domain.position.Rank.THREE;
import static chess.domain.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class PositionTest {

    private final Position E_FOUR = Position.of(E, FOUR);

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

        final boolean actual = E_FOUR.isInCrossPosition(otherPosition);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"A, FOUR, false", "E, ONE, false", "C, SIX, true", "G, TWO, true"})
    @DisplayName("대각선 위치에 있는지 확인한다")
    void diagonal_position_check_test(final File file, final Rank rank, final boolean expected) {
        final Position otherPosition = Position.of(file, rank);

        final boolean actual = E_FOUR.isInDiagonalPosition(otherPosition);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"D, FOUR, 1", "F, FIVE, 2", "G, FIVE, 3"})
    @DisplayName("맨허튼 거리를 계산한다")
    void manhattan_distance_calculate_test(final File file, final Rank rank, final int expectedDistance) {
        final Position otherPosition = Position.of(file, rank);

        final int actualDistance = E_FOUR.calculateManhattanDistance(otherPosition);

        assertThat(actualDistance).isEqualTo(expectedDistance);
    }

    @ParameterizedTest
    @MethodSource("provideOtherPositionAndExpectedPassingPositions")
    @DisplayName("입력 받은 위치로 가는 경로에 있는 위치들을 반환한다")
    void find_passing_position_test(final Position otherPosition, final List<Position> expectedPassingPositions) {
        final List<Position> actualPassingPositions = E_FOUR.findPassingPositions(otherPosition);

        assertThat(actualPassingPositions).containsAll(expectedPassingPositions);
    }

    private static Stream<Arguments> provideOtherPositionAndExpectedPassingPositions() {
        return Stream.of(
                Arguments.of(Position.of(B, FOUR), List.of(Position.of(D, FOUR), Position.of(C, FOUR))),
                Arguments.of(Position.of(E, TWO), List.of(Position.of(E, THREE))),
                Arguments.of(Position.of(F, FIVE), List.of()),
                Arguments.of(Position.of(H, ONE), List.of(Position.of(F, THREE), Position.of(G, TWO))),
                Arguments.of(Position.of(B, SEVEN), List.of(Position.of(D, FIVE), Position.of(C, SIX)))
        );
    }

    @ParameterizedTest
    @CsvSource({"FOUR, true", "SIX, false"})
    @DisplayName("Rank가 높은지 확인한다.")
    void upper_rank_check_test(final Rank otherRank, final boolean expected) {
        final Position position = Position.of(D, FIVE);
        final Position otherPosition = Position.of(D, otherRank);

        final boolean actual = position.isUpperRankThan(otherPosition);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"FOUR, false", "SIX, true"})
    @DisplayName("Rank가 낮은지 확인한다.")
    void lower_rank_check_test(final Rank otherRank, final boolean expected) {
        final Position position = Position.of(D, FIVE);
        final Position otherPosition = Position.of(D, otherRank);

        final boolean actual = position.isLowerRankThan(otherPosition);

        assertThat(actual).isEqualTo(expected);
    }
}
