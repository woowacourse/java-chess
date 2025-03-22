package chess.piece;

import static chess.Fixtures.C3;
import static chess.Fixtures.C5;
import static chess.Fixtures.D4;
import static chess.Fixtures.E3;
import static chess.Fixtures.E5;
import static chess.Movement.DOWN_DOWN_LEFT;
import static chess.Movement.DOWN_DOWN_RIGHT;
import static chess.Movement.LEFT_DOWN;
import static chess.Movement.LEFT_LEFT_DOWN;
import static chess.Movement.LEFT_LEFT_UP;
import static chess.Movement.LEFT_UP;
import static chess.Movement.RIGHT_DOWN;
import static chess.Movement.RIGHT_RIGHT_DOWN;
import static chess.Movement.RIGHT_RIGHT_UP;
import static chess.Movement.RIGHT_UP;
import static chess.Movement.UP_UP_LEFT;
import static chess.Movement.UP_UP_RIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.Movement;
import chess.Position;

class BishopTest {

    @MethodSource
    @ParameterizedTest
    void 비숍은_원하는_만큼_대각선으로_이동한다(Movement movement, Position position) {
        Bishop bishop = new Bishop(Team.A, D4);

        assertThat(bishop.move(movement)).isEqualTo(new Bishop(Team.A, position));
    }

    private static Stream<Arguments> 비숍은_원하는_만큼_대각선으로_이동한다() {
        return Stream.of(
                Arguments.of(LEFT_UP, C5),
                Arguments.of(RIGHT_UP, E5),
                Arguments.of(LEFT_DOWN, C3),
                Arguments.of(RIGHT_DOWN, E3)
        );
    }

    @MethodSource
    @ParameterizedTest
    void 바숍이_이동할_수_없다(Movement movement) {
        Bishop bishop = new Bishop(Team.A, D4);

        assertThatThrownBy(() -> bishop.move(movement))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> 바숍이_이동할_수_없다() {
        return Stream.of(
                Arguments.of(LEFT_LEFT_UP),
                Arguments.of(LEFT_LEFT_DOWN),
                Arguments.of(RIGHT_RIGHT_DOWN),
                Arguments.of(RIGHT_RIGHT_UP),
                Arguments.of(UP_UP_LEFT),
                Arguments.of(UP_UP_RIGHT),
                Arguments.of(DOWN_DOWN_LEFT),
                Arguments.of(DOWN_DOWN_RIGHT)
        );
    }

}
