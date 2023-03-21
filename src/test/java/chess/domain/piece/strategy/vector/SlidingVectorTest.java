package chess.domain.piece.strategy.vector;

import static chess.domain.board.File.D;
import static chess.domain.board.File.E;
import static chess.domain.board.Rank.FOUR;
import static chess.domain.board.Rank.THREE;
import static chess.domain.piece.strategy.vector.SlidingVector.EAST;
import static chess.domain.piece.strategy.vector.SlidingVector.NORTH;
import static chess.domain.piece.strategy.vector.SlidingVector.NORTHEAST;
import static chess.domain.piece.strategy.vector.SlidingVector.NORTHWEST;
import static chess.domain.piece.strategy.vector.SlidingVector.SOUTH;
import static chess.domain.piece.strategy.vector.SlidingVector.SOUTHEAST;
import static chess.domain.piece.strategy.vector.SlidingVector.SOUTHWEST;
import static chess.domain.piece.strategy.vector.SlidingVector.WEST;
import static chess.domain.piece.strategy.vector.SlidingVector.ofBishop;
import static chess.domain.piece.strategy.vector.SlidingVector.ofQueen;
import static chess.domain.piece.strategy.vector.SlidingVector.ofRook;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.board.File;
import chess.domain.board.Rank;
import java.util.List;
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
class SlidingVectorTest {

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
    void 가는_경로인지_확인한다(final SlidingVector slidingVector, final int file, final int rank, final boolean result) {
        assertThat(slidingVector.isOnMyWay(file, rank)).isEqualTo(result);
    }

    @Test
    void 비숍에_해당하는_벡터들을_반환한다() {
        final List<SlidingVector> vectors = ofBishop();

        assertThat(vectors).containsExactly(NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST);
    }

    @Test
    void 퀸에_해당하는_벡터들을_반환한다() {
        final List<SlidingVector> vectors = ofQueen();

        assertThat(vectors).containsExactly(NORTH, SOUTH, EAST, WEST, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST);
    }

    @Test
    void 룩에_해당하는_벡터들을_반환한다() {
        final List<SlidingVector> vectors = ofRook();

        assertThat(vectors).containsExactly(NORTH, SOUTH, EAST, WEST);
    }

    @Test
    void 해당_방향의_다음_파일을_반환한다() {
        assertThat(SOUTHEAST.next(D)).isEqualTo(E);
    }

    @ParameterizedTest
    @CsvSource(value = {"EAST:H", "WEST:A"}, delimiter = ':')
    void 다음_파일로_갈_수_없으면_예외를_던진다(final SlidingVector slidingVector, final File file) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> slidingVector.next(file));
    }

    @Test
    void 해당_방향의_다음_랭크를_반환한다() {
        assertThat(SOUTHEAST.next(FOUR)).isEqualTo(THREE);
    }

    @ParameterizedTest
    @CsvSource(value = {"NORTH:EIGHT", "SOUTH:ONE"}, delimiter = ':')
    void 다음_랭크로_갈_수_없으면_예외를_던진다(final SlidingVector slidingVector, final Rank rank) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> slidingVector.next(rank));
    }
}
