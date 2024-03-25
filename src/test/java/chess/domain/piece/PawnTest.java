package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static chess.domain.piece.PositionFixture.FROM;
import static chess.domain.piece.PositionFixture.LEFT_UP;
import static chess.domain.piece.PositionFixture.RIGHT_UP;
import static chess.domain.piece.PositionFixture.UP;
import static chess.domain.piece.PositionFixture.UP_UP;
import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @ParameterizedTest
    @MethodSource("movablePositionStream")
    @DisplayName("움직이는 위치와 잡는 위치가 다르다(움직일 수 있을 때)")
    void should_not_catchable_when_movable(Position to) {
        WhitePawn pawn = new WhitePawn();

        assertThat(pawn.isMovable(FROM, to))
                .isNotEqualTo(pawn.isCatchable(FROM, to))
                .isTrue();
    }

    @ParameterizedTest
    @MethodSource("catchablePositionStream")
    @DisplayName("움직이는 위치와 잡는 위치가 다르다(잡을 수 있을 때)")
    void should_not_movable_when_catchable(Position to) {
        WhitePawn pawn = new WhitePawn();

        assertThat(pawn.isCatchable(FROM, to))
                .isNotEqualTo(pawn.isMovable(FROM, to))
                .isTrue();
    }

    private static Stream<Position> movablePositionStream() {
        return Stream.of(
                UP, UP_UP
        );
    }

    private static Stream<Position> catchablePositionStream() {
        return Stream.of(
                LEFT_UP, RIGHT_UP
        );
    }
}
