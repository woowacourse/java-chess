package chess.piece;

import static chess.Movement.DOWN;
import static chess.Movement.LEFT;
import static chess.Movement.LEFT_DOWN;
import static chess.Movement.LEFT_LEFT_DOWN;
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

class QueenTest {

    @MethodSource
    @ParameterizedTest
    void 퀸은_일직선으로_원하는_만큼_이동한다(Movement movement, int moveCount, Position position) {
        Queen queen = new Queen(new Position(Row.THREE, Column.D));

        assertThat(queen.move(movement, moveCount)).isEqualTo(new Queen(position));
    }

    private static Stream<Arguments> 퀸은_일직선으로_원하는_만큼_이동한다() {
        return Stream.of(
                Arguments.of(LEFT, 2, new Position(Row.THREE, Column.B)),
                Arguments.of(RIGHT, 2, new Position(Row.THREE, Column.F)),
                Arguments.of(UP, 2, new Position(Row.FIVE, Column.D)),
                Arguments.of(DOWN, 2, new Position(Row.ONE, Column.D))
        );
    }

    @MethodSource
    @ParameterizedTest
    void 퀸은_대각선으로_원하는_만큼_이동한다(Movement movement, int moveCount, Position position) {
        Queen queen = new Queen(new Position(Row.THREE, Column.D));

        assertThat(queen.move(movement, moveCount)).isEqualTo(new Queen(position));
    }

    private static Stream<Arguments> 퀸은_대각선으로_원하는_만큼_이동한다() {
        return Stream.of(
                Arguments.of(LEFT_UP, 2, new Position(Row.FIVE, Column.B)),
                Arguments.of(RIGHT_UP, 2, new Position(Row.FIVE, Column.F)),
                Arguments.of(LEFT_DOWN, 2, new Position(Row.ONE, Column.B)),
                Arguments.of(RIGHT_DOWN, 2, new Position(Row.ONE, Column.F))
        );
    }

    @MethodSource
    @ParameterizedTest
    void 퀸이_움직일_수_없다(Movement movement, int moveCount) {
        Queen queen = new Queen(new Position(Row.ONE, Column.A));

        assertThatThrownBy(() -> queen.move(movement, moveCount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> 퀸이_움직일_수_없다() {
        return Stream.of(
                Arguments.of(LEFT_LEFT_DOWN, 1),
                Arguments.of(LEFT, 1),
                Arguments.of(DOWN, 1),
                Arguments.of(LEFT_DOWN, 1),
                Arguments.of(LEFT_UP, 1),
                Arguments.of(RIGHT_DOWN, 1),
                Arguments.of(UP, 9),
                Arguments.of(RIGHT, 9),
                Arguments.of(RIGHT_UP, 9)
        );
    }

}
