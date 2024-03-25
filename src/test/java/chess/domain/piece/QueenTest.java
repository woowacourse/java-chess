package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static chess.domain.piece.PositionFixture.DOWN;
import static chess.domain.piece.PositionFixture.FROM;
import static chess.domain.piece.PositionFixture.LEFT;
import static chess.domain.piece.PositionFixture.LEFT_DIAGONAL;
import static chess.domain.piece.PositionFixture.LEFT_DOWN;
import static chess.domain.piece.PositionFixture.LEFT_LEFT_UP;
import static chess.domain.piece.PositionFixture.LEFT_UP;
import static chess.domain.piece.PositionFixture.LEFT_UP_UP;
import static chess.domain.piece.PositionFixture.RIGHT;
import static chess.domain.piece.PositionFixture.RIGHT_DIAGONAL;
import static chess.domain.piece.PositionFixture.RIGHT_DOWN;
import static chess.domain.piece.PositionFixture.RIGHT_RIGHT_UP;
import static chess.domain.piece.PositionFixture.RIGHT_STRAIGHT;
import static chess.domain.piece.PositionFixture.RIGHT_UP;
import static chess.domain.piece.PositionFixture.RIGHT_UP_UP;
import static chess.domain.piece.PositionFixture.UP;
import static chess.domain.piece.PositionFixture.UP_STRAIGHT;
import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {

    @ParameterizedTest
    @MethodSource("movablePositionStream")
    @DisplayName("움직일 수 있다면 잡을 수 있다")
    void should_catchable_when_movable(Position to) {
        Queen queen = new Queen(Color.BLACK);

        assertThat(queen.isCatchable(FROM, to))
                .isEqualTo(queen.isCatchable(FROM, to))
                .isTrue();
    }

    @ParameterizedTest
    @MethodSource("immovablePositionStream")
    @DisplayName("움직일 수 없다면 잡을 수 없다")
    void should_not_catchable_when_not_movable(Position to) {
        Queen queen = new Queen(Color.BLACK);

        assertThat(queen.isMovable(FROM, to))
                .isEqualTo(queen.isCatchable(FROM, to))
                .isFalse();
    }

    private static Stream<Position> movablePositionStream() {
        return Stream.of(
                LEFT_DOWN, LEFT_UP,
                RIGHT_DOWN, RIGHT_UP,
                LEFT_DIAGONAL, RIGHT_DIAGONAL,
                LEFT, RIGHT,
                UP, DOWN,
                RIGHT_STRAIGHT, UP_STRAIGHT
        );
    }

    private static Stream<Position> immovablePositionStream() {
        return Stream.of(
                LEFT_UP_UP, LEFT_LEFT_UP,
                RIGHT_UP_UP, RIGHT_RIGHT_UP
        );
    }
}
