package chess.model.piece;

import static chess.model.piece.Direction.EAST;
import static chess.model.piece.Direction.NORTH;
import static chess.model.piece.Direction.NORTH_EAST;
import static chess.model.piece.Direction.NORTH_NORTH_EAST;
import static chess.model.piece.Direction.NORTH_NORTH_WEST;
import static chess.model.piece.Direction.NORTH_WEST;
import static chess.model.piece.Direction.NORTH_WEST_WEST;
import static chess.model.piece.Direction.SOUTH;
import static chess.model.piece.Direction.SOUTH_EAST;
import static chess.model.piece.Direction.SOUTH_SOUTH_EAST;
import static chess.model.piece.Direction.SOUTH_SOUTH_WEST;
import static chess.model.piece.Direction.SOUTH_WEST;
import static chess.model.piece.Direction.WEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DirectionTest {

    @Nested
    @DisplayName("findDirection() 테스트")
    class FindDirectionMethodTest {

        @Test
        @DisplayName("유효한 rank, file을 전달하면 그 방향에 맞는 Direction을 반환한다")
        void findDirection_givenValidRankAndFile_thenSuccess() {
            // when
            final Direction actual = Direction.findDirection(3, 3);

            // then
            assertThat(actual).isSameAs(Direction.NORTH_EAST);
        }

        @Test
        @DisplayName("유효하지 않은 rank, file을 전달하면 예외가 발생한다")
        void findDirection_gievnInvalidRankAndFile_thenFail() {
            assertThatThrownBy(() -> Direction.findDirection(5, 7))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("방향을 찾을 수 없습니다.");
        }
    }

    @ParameterizedTest(name = "match()는 Direction.{0}이고 rank={1}, file={2}이면 {3}을 반환한다.")
    @DisplayName("match() 테스트")
    @MethodSource("provideMatchArguments")
    void match_givenRankAndFile_thenReturnResult(
            final Direction direction,
            final int rank,
            final int file,
            final boolean result
    ) {
        assertThat(direction.match(rank, file)).isEqualTo(result);
    }

    private static Stream<Arguments> provideMatchArguments() {
        return Stream.of(
                Arguments.of(SOUTH, 0, -1, true), Arguments.of(NORTH_WEST, -3, 3, true),
                Arguments.of(NORTH_WEST_WEST, -4, 2, true)
        );
    }

    @ParameterizedTest(name = "findDirection()은 rank = {0}, file = {1}일 때 Direction.{2}를 반환한다.")
    @DisplayName("findDirection() 테스트")
    @MethodSource("provideDirection")
    void findDirection_givenRankAndFile_thenReturnDirection(final int rank, final int file, final Direction expected) {
        final Direction direction = Direction.findDirection(file, rank);

        assertThat(direction).isEqualTo(expected);
    }

    private static Stream<Arguments> provideDirection() {
        return Stream.of(
                Arguments.of(0, 1, NORTH), Arguments.of(1, 0, EAST), Arguments.of(0, -1, SOUTH),
                Arguments.of(-1, 0, WEST), Arguments.of(1, 1, NORTH_EAST), Arguments.of(-1, 1, NORTH_WEST),
                Arguments.of(1, -1, SOUTH_EAST), Arguments.of(-1, -1, SOUTH_WEST), Arguments.of(1, 2, NORTH_NORTH_EAST),
                Arguments.of(-1, 2, NORTH_NORTH_WEST), Arguments.of(1, -2, SOUTH_SOUTH_EAST),
                Arguments.of(-1, -2, SOUTH_SOUTH_WEST), Arguments.of(-2, 1, NORTH_WEST_WEST),
                Arguments.of(2, 1, Direction.NORTH_EAST_EAST), Arguments.of(-2, -1, Direction.SOUTH_WEST_WEST),
                Arguments.of(2, -1, Direction.SOUTH_EAST_EAST)
        );
    }
}
