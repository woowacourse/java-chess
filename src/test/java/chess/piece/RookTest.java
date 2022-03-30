package chess.piece;

import static chess.position.File.A;
import static chess.position.File.B;
import static chess.position.File.C;
import static chess.position.File.H;
import static chess.position.Rank.EIGHT;
import static chess.position.Rank.FIVE;
import static chess.position.Rank.FOUR;
import static chess.position.Rank.ONE;
import static chess.position.Rank.SEVEN;
import static chess.position.Rank.THREE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.piece.movementcondition.BaseMovementCondition;
import chess.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RookTest {

    @ParameterizedTest
    @MethodSource("provideMoveCollinearRook")
    @DisplayName("룩은 동일선상으로 제한 없이 이동")
    void moveRookCollinearPositionUnlimitedDistance(Position from, Position to) {
        Rook rook = new Rook(Color.BLACK);

        assertThat(rook.identifyMovementCondition(from, to))
                .isEqualTo(BaseMovementCondition.MUST_OBSTACLE_FREE);
    }

    private static Stream<Arguments> provideMoveCollinearRook() {
        return Stream.of(
                Arguments.of(new Position(A, EIGHT), new Position(A, FIVE)),
                Arguments.of(new Position(H, EIGHT), new Position(C, EIGHT)),
                Arguments.of(new Position(H, ONE), new Position(H, THREE)),
                Arguments.of(new Position(A, ONE), new Position(A, FOUR))
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidMoveRook")
    @DisplayName("룩이 전후양옆외의 방향으로 이동 시 예외 발생")
    void throwExceptionWhenRookMoveInvalidPosition(Position from, Position to) {
        Rook rook = new Rook(Color.BLACK);

        assertThat(rook.identifyMovementCondition(from, to))
                .isEqualTo(BaseMovementCondition.IMPOSSIBLE);
    }

    private static Stream<Arguments> provideInvalidMoveRook() {
        return Stream.of(
                Arguments.of(new Position(A, EIGHT), new Position(B, FIVE)),
                Arguments.of(new Position(H, EIGHT), new Position(C, SEVEN)),
                Arguments.of(new Position(H, ONE), new Position(B, THREE))
        );
    }
}
