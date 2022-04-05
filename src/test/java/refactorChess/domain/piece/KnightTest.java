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

class KnightTest {

    @Test
    void createValidPieceOfKnight() {
        assertThat(new Knight(PieceColor.WHITE, Position.valueOf("b1"))).isInstanceOf(Knight.class);
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("validKnightMovableTestSet")
    @DisplayName("나이트가 이동하는 방향을 구할 수 있다.")
    void validMovablePieceOfKnight(Position from, Position to, Direction direction) {
        final Knight knight = new Knight(PieceColor.WHITE, from);

        assertThat(knight.findByDirection(from, to)).isEqualTo(direction);
    }

    static Stream<Arguments> validKnightMovableTestSet() {
        return Stream.of(
                Arguments.of(Position.valueOf("b1"), Position.valueOf("c3"), Direction.NORTH_NORTH_EAST),
                Arguments.of(Position.valueOf("b1"), Position.valueOf("a3"), Direction.NORTH_NORTH_WEST),
                Arguments.of(Position.valueOf("c2"), Position.valueOf("e3"), Direction.NORTH_EAST_EAST),
                Arguments.of(Position.valueOf("c2"), Position.valueOf("a3"), Direction.NORTH_WEST_WEST),
                Arguments.of(Position.valueOf("b3"), Position.valueOf("c1"), Direction.SOUTH_SOUTH_EAST),
                Arguments.of(Position.valueOf("b3"), Position.valueOf("a1"), Direction.SOUTH_SOUTH_WEST),
                Arguments.of(Position.valueOf("c2"), Position.valueOf("e1"), Direction.SOUTH_EAST_EAST),
                Arguments.of(Position.valueOf("c2"), Position.valueOf("a1"), Direction.SOUTH_WEST_WEST)
        );
    }
}
