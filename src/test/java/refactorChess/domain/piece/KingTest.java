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

class KingTest {

    @Test
    @DisplayName("킹을 생성할 수 있다.")
    void createValidPieceOfKing() {
        assertThat(new King(PieceColor.WHITE, Position.valueOf("c1"))).isInstanceOf(King.class);
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("validKingMovableTestSet")
    @DisplayName("킹이 이동하는 방향을 구할 수 있다.")
    void validMovablePieceOfKing(Position from, Position to, Direction direction) {
        final King king = new King(PieceColor.WHITE, from);

        assertThat(king.findByDirection(from, to)).isEqualTo(direction);
    }

    static Stream<Arguments> validKingMovableTestSet() {
        return Stream.of(
                Arguments.of(Position.valueOf("d1"), Position.valueOf("d2"), Direction.NORTH),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("d2"), Direction.SOUTH),
                Arguments.of(Position.valueOf("d1"), Position.valueOf("c1"), Direction.WEST),
                Arguments.of(Position.valueOf("a1"), Position.valueOf("b1"), Direction.EAST),
                Arguments.of(Position.valueOf("d1"), Position.valueOf("e2"), Direction.NORTH_EAST),
                Arguments.of(Position.valueOf("d1"), Position.valueOf("c2"), Direction.NORTH_WEST),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("e2"), Direction.SOUTH_EAST),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("c2"), Direction.SOUTH_WEST)
        );
    }
}
