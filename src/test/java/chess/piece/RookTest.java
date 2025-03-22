package chess.piece;

import static chess.Movement.DOWN;
import static chess.Movement.LEFT;
import static chess.Movement.LEFT_DOWN;
import static chess.Movement.LEFT_UP;
import static chess.Movement.RIGHT;
import static chess.Movement.RIGHT_DOWN;
import static chess.Movement.RIGHT_UP;
import static chess.Movement.UP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.Column;
import chess.Movement;
import chess.Position;
import chess.Row;

class RookTest {

    @MethodSource
    @ParameterizedTest
    void 룩은_원하는_만큼_직선으로_이동할_수_있다(Movement movement, int moveCount, Position position) {
        Rook rook = new Rook(new Position(Row.THREE, Column.D));

        assertThat(rook.move(movement, moveCount)).isEqualTo(new Rook(position));
    }

    private static Stream<Arguments> 룩은_원하는_만큼_직선으로_이동할_수_있다() {
        return Stream.of(
                Arguments.of(UP, 2, new Position(Row.FIVE, Column.D)),
                Arguments.of(LEFT, 2, new Position(Row.THREE, Column.B)),
                Arguments.of(RIGHT, 2, new Position(Row.THREE, Column.F)),
                Arguments.of(DOWN, 2, new Position(Row.ONE, Column.D))
        );
    }

    @MethodSource
    @ParameterizedTest
    void 룩이_이동할_수_없다(Movement movement, int moveCount) {
        Rook rook = new Rook(new Position(Row.ONE, Column.A));

        assertThatThrownBy(() -> rook.move(movement, moveCount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> 룩이_이동할_수_없다() {
        return Stream.of(
                Arguments.of(LEFT_UP, 2),
                Arguments.of(RIGHT_UP, 2),
                Arguments.of(LEFT_DOWN, 2),
                Arguments.of(RIGHT_DOWN, 2),
                Arguments.of(UP, 8),
                Arguments.of(LEFT, 1),
                Arguments.of(RIGHT, 8),
                Arguments.of(DOWN, 1)
        );
    }

}
