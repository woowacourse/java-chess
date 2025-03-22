package chess.piece;

import static chess.Fixtures.A1;
import static chess.Fixtures.A7;
import static chess.Fixtures.D4;
import static chess.Fixtures.G1;
import static chess.Fixtures.H8;
import static chess.Movement.LEFT_DOWN;
import static chess.Movement.LEFT_UP;
import static chess.Movement.RIGHT_DOWN;
import static chess.Movement.RIGHT_UP;
import static chess.Movement.UP;
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
    void 비숍은_원하는_만큼_대각선으로_이동한다(Movement movement, int moveCount, Position position) {
        Bishop bishop = new Bishop(D4);

        assertThat(bishop.move(movement, moveCount)).isEqualTo(new Bishop(position));
    }

    private static Stream<Arguments> 비숍은_원하는_만큼_대각선으로_이동한다() {
        return Stream.of(
                Arguments.of(LEFT_UP, 3, A7),
                Arguments.of(RIGHT_UP, 4, H8),
                Arguments.of(LEFT_DOWN, 3, A1),
                Arguments.of(RIGHT_DOWN, 3, G1)
        );
    }

    @MethodSource
    @ParameterizedTest
    void 바숍이_이동할_수_없다(Movement movement, int moveCount) {
        Bishop bishop = new Bishop(D4);

        assertThatThrownBy(() -> bishop.move(movement, moveCount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> 바숍이_이동할_수_없다() {
        return Stream.of(
                Arguments.of(LEFT_UP, 4),
                Arguments.of(RIGHT_UP, 5),
                Arguments.of(LEFT_DOWN, 4),
                Arguments.of(RIGHT_DOWN, 4),
                Arguments.of(UP, 1)
        );
    }

}
