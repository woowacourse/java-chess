package chess.domain.piece;

import static chess.domain.piece.DirectionVector.EAST;
import static chess.domain.piece.DirectionVector.NORTH;
import static chess.domain.piece.DirectionVector.NORTHEAST;
import static chess.domain.piece.DirectionVector.NORTHWEST;
import static chess.domain.piece.DirectionVector.SOUTH;
import static chess.domain.piece.DirectionVector.SOUTHEAST;
import static chess.domain.piece.DirectionVector.SOUTHWEST;
import static chess.domain.piece.DirectionVector.WEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.board.File;
import chess.domain.board.Rank;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DirectionVectorTest {

    private static Stream<Arguments> generateDirections() {
        return Stream.of(
                Arguments.of(NORTH, 0, 2, true),
                Arguments.of(NORTH, 1, 1, false),
                Arguments.of(SOUTH, 0, -3, true),
                Arguments.of(SOUTH, 1, 1, false),
                Arguments.of(WEST, -2, 0, true),
                Arguments.of(WEST, 2, 0, false),
                Arguments.of(EAST, 2, 0, true),
                Arguments.of(EAST, -2, 0, false),
                Arguments.of(SOUTHEAST, 2, -2, true),
                Arguments.of(SOUTHEAST, -2, 2, false),
                Arguments.of(SOUTHWEST, -2, -2, true),
                Arguments.of(SOUTHWEST, 2, 2, false),
                Arguments.of(NORTHEAST, 2, 2, true),
                Arguments.of(NORTHEAST, -2, -2, false),
                Arguments.of(NORTHWEST, -2, 2, true),
                Arguments.of(NORTHWEST, 2, -2, false)
        );
    }

    @ParameterizedTest
    @MethodSource("generateDirections")
    void 가는_경로인지_확인한다(final DirectionVector directionVector, final int x, final int y, final boolean result) {
        assertThat(directionVector.isOnMyWay(x, y)).isEqualTo(result);
    }

    @Test
    void 해당_방향의_다음_파일을_반환한다() {
        final DirectionVector directionVector = SOUTHEAST;
        final File d = File.D;

        assertThat(directionVector.next(d)).isEqualTo(File.E);
    }

    @ParameterizedTest
    @CsvSource(value = {"EAST:H", "WEST:A"}, delimiter = ':')
    void 다음_파일로_갈_수_없으면_예외를_던진다(final DirectionVector directionVector, final File file) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> directionVector.next(file));
    }

    @Test
    void 해당_방향의_다음_랭크를_반환한다() {
        final DirectionVector directionVector = SOUTHEAST;
        final Rank four = Rank.FOUR;

        assertThat(directionVector.next(four)).isEqualTo(Rank.THREE);
    }

    @ParameterizedTest
    @CsvSource(value = {"NORTH:EIGHT", "SOUTH:ONE"}, delimiter = ':')
    void 다음_랭크로_갈_수_없으면_예외를_던진다(final DirectionVector directionVector, final Rank rank) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> directionVector.next(rank));
    }
}
