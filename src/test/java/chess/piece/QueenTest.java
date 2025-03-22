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
    void 퀸은_일직선으로_원하는_만큼_이동한다(Movement movement, Position position) {
        Queen queen = new Queen(Team.A, new Position(Row.THREE, Column.D));

        assertThat(queen.move(movement)).isEqualTo(new Queen(Team.A, position));
    }

    private static Stream<Arguments> 퀸은_일직선으로_원하는_만큼_이동한다() {
        return Stream.of(
                Arguments.of(LEFT, new Position(Row.THREE, Column.C)),
                Arguments.of(RIGHT, new Position(Row.THREE, Column.E)),
                Arguments.of(UP, new Position(Row.FOUR, Column.D)),
                Arguments.of(DOWN, new Position(Row.TWO, Column.D))
        );
    }

    @MethodSource
    @ParameterizedTest
    void 퀸은_대각선으로_원하는_만큼_이동한다(Movement movement, Position position) {
        Queen queen = new Queen(Team.A, new Position(Row.THREE, Column.D));

        assertThat(queen.move(movement)).isEqualTo(new Queen(Team.A, position));
    }

    private static Stream<Arguments> 퀸은_대각선으로_원하는_만큼_이동한다() {
        return Stream.of(
                Arguments.of(LEFT_UP, new Position(Row.FOUR, Column.C)),
                Arguments.of(RIGHT_UP, new Position(Row.FOUR, Column.E)),
                Arguments.of(LEFT_DOWN, new Position(Row.TWO, Column.C)),
                Arguments.of(RIGHT_DOWN, new Position(Row.TWO, Column.E))
        );
    }

    @MethodSource
    @ParameterizedTest
    void 퀸이_움직일_수_없다(Movement movement) {
        Queen queen = new Queen(Team.A, new Position(Row.ONE, Column.A));

        assertThatThrownBy(() -> queen.move(movement))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> 퀸이_움직일_수_없다() {
        return Stream.of(
                Arguments.of(LEFT_LEFT_DOWN),
                Arguments.of(LEFT),
                Arguments.of(DOWN),
                Arguments.of(LEFT_DOWN),
                Arguments.of(LEFT_UP),
                Arguments.of(RIGHT_DOWN)
        );
    }

}
