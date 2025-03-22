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
    void 룩은_원하는_만큼_직선으로_이동할_수_있다(Movement movement, Position position) {
        Rook rook = new Rook(Team.A, new Position(Row.THREE, Column.D));

        assertThat(rook.move(movement)).isEqualTo(new Rook(Team.A, position));
    }

    private static Stream<Arguments> 룩은_원하는_만큼_직선으로_이동할_수_있다() {
        return Stream.of(
                Arguments.of(UP, new Position(Row.FOUR, Column.D)),
                Arguments.of(LEFT, new Position(Row.THREE, Column.C)),
                Arguments.of(RIGHT, new Position(Row.THREE, Column.E)),
                Arguments.of(DOWN, new Position(Row.TWO, Column.D))
        );
    }

    @MethodSource
    @ParameterizedTest
    void 룩이_이동할_수_없다(Movement movement) {
        Rook rook = new Rook(Team.A, new Position(Row.ONE, Column.A));

        assertThatThrownBy(() -> rook.move(movement))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> 룩이_이동할_수_없다() {
        return Stream.of(
                Arguments.of(LEFT_UP),
                Arguments.of(RIGHT_UP),
                Arguments.of(LEFT_DOWN),
                Arguments.of(RIGHT_DOWN)
        );
    }

}
