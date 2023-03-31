package chess.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.domain.File.*;
import static chess.domain.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class PositionTest {

    private final Position E_FOUR = new Position(E, FOUR);

    @Test
    void File과_Rank정보로_위치를_만든다() {
        assertThatNoException().isThrownBy(() -> new Position(A, ONE));
    }

    @ParameterizedTest
    @CsvSource({"A, FOUR, true", "E, ONE, true", "C, SIX, false", "G, TWO, false"})
    void 상하좌우_위치에_있는지_확인한다(final File file, final Rank rank, final boolean expected) {
        //given
        final Position otherPosition = new Position(file, rank);

        //when
        final boolean actual = E_FOUR.isInCrossPosition(otherPosition);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"A, FOUR, false", "E, ONE, false", "C, SIX, true", "G, TWO, true"})
    void 대각선_위치에_있는지_확인한다(final File file, final Rank rank, final boolean expected) {
        //given
        final Position otherPosition = new Position(file, rank);

        //when
        final boolean actual = E_FOUR.isInDiagonalPosition(otherPosition);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"D, FOUR, 1", "F, FIVE, 2", "G, FIVE, 3"})
    void 맨허튼_거리를_계산한다(final File file, final Rank rank, final int expectedDistance) {
        //given
        final Position otherPosition = new Position(file, rank);

        //when
        final int actualDistance = E_FOUR.calculateManhattanDistance(otherPosition);

        //then
        assertThat(actualDistance).isEqualTo(expectedDistance);
    }

    @ParameterizedTest
    @MethodSource("provideOtherPositionAndExpectedPassingPositions")
    void 입력_받은_위치로_가는_경로에_있는_위치들을_반환한다(final Position otherPosition, final List<Position> expectedPassingPositions) {
        //given
        //when
        final List<Position> actualPassingPositions = E_FOUR.findPassingPositions(otherPosition);

        //then
        assertThat(actualPassingPositions).containsAll(expectedPassingPositions);
    }

    private static Stream<Arguments> provideOtherPositionAndExpectedPassingPositions() {
        return Stream.of(
                Arguments.of(new Position(B, FOUR), List.of(new Position(D, FOUR), new Position(C, FOUR))),
                Arguments.of(new Position(E, TWO), List.of(new Position(E, THREE))),
                Arguments.of(new Position(F, FIVE), List.of()),
                Arguments.of(new Position(H, ONE), List.of(new Position(F, THREE), new Position(G, TWO))),
                Arguments.of(new Position(B, SEVEN), List.of(new Position(D, FIVE), new Position(C, SIX)))
        );
    }

    @ParameterizedTest
    @CsvSource({"FOUR, true", "SIX, false"})
    void Rank가_높은지_확인한다(final Rank otherRank, final boolean expected) {
        //given
        final Position position = new Position(D, FIVE);
        final Position otherPosition = new Position(D, otherRank);

        //when
        final boolean actual = position.isUpperRankThan(otherPosition);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"FOUR, false", "SIX, true"})
    void Rank가_낮은지_확인한다(final Rank otherRank, final boolean expected) {
        //given
        final Position position = new Position(D, FIVE);
        final Position otherPosition = new Position(D, otherRank);

        //when
        final boolean actual = position.isLowerRankThan(otherPosition);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"A, true", "B, false"})
    void 같은_파일인지_확인한다(final File file, final boolean expected) {
        //given
        final Position position = new Position(A, FIVE);

        //when
        final boolean actual = position.isSameFile(file);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
