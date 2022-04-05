package refactorChess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import refactorChess.domain.board.Direction;
import refactorChess.domain.board.Position;

class QueenTest {

    @Test
    @DisplayName("퀸을 생성할 수 있다.")
    void createValidPieceOfQueen() {
        assertThat(new Queen(PieceColor.WHITE, Position.valueOf("d1"))).isInstanceOf(Queen.class);
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("validQueenMovableTestSet")
    @DisplayName("퀸이 이동하는 방향을 구할 수 있다.")
    void validMovablePieceOfQueen(Position from, Position to, Direction direction) {
        final Queen queen = new Queen(PieceColor.WHITE, from);

        assertThat(queen.findByDirection(from, to)).isEqualTo(direction);
    }

    static Stream<Arguments> validQueenMovableTestSet() {
        return Stream.of(
                Arguments.of(Position.valueOf("d1"), Position.valueOf("d3"), Direction.NORTH),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("d1"), Direction.SOUTH),
                Arguments.of(Position.valueOf("d1"), Position.valueOf("a1"), Direction.WEST),
                Arguments.of(Position.valueOf("a1"), Position.valueOf("d1"), Direction.EAST),
                Arguments.of(Position.valueOf("d1"), Position.valueOf("f3"), Direction.NORTH_EAST),
                Arguments.of(Position.valueOf("d1"), Position.valueOf("b3"), Direction.NORTH_WEST),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("f1"), Direction.SOUTH_EAST),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("b1"), Direction.SOUTH_WEST)
        );
    }
}
