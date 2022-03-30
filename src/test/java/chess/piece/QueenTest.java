package chess.piece;

import static chess.position.File.A;
import static chess.position.File.B;
import static chess.position.File.D;
import static chess.position.File.E;
import static chess.position.File.F;
import static chess.position.File.H;
import static chess.position.Rank.EIGHT;
import static chess.position.Rank.FIVE;
import static chess.position.Rank.FOUR;
import static chess.position.Rank.ONE;
import static chess.position.Rank.SIX;
import static chess.position.Rank.THREE;
import static chess.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import chess.piece.movementcondition.BaseMovementCondition;
import chess.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class QueenTest {

    @ParameterizedTest
    @MethodSource("provideInvalidMoveQueen")
    @DisplayName("퀸은 동일선상외에는 이동 시 예외 발생")
    void moveInvalidMoveQueen(Position from, Position to) {
        Queen queen = new Queen(Color.BLACK);

        assertThat(queen.identifyMovementCondition(from, to))
                .isEqualTo(BaseMovementCondition.IMPOSSIBLE);
    }

    private static Stream<Arguments> provideInvalidMoveQueen() {
        return Stream.of(
                Arguments.of(new Position(D, EIGHT), new Position(F, FOUR)),
                Arguments.of(new Position(D, EIGHT), new Position(A, SIX)),
                Arguments.of(new Position(D, ONE), new Position(E, FIVE)),
                Arguments.of(new Position(D, ONE), new Position(B, TWO))
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidMoveQueen")
    @DisplayName("퀸은 동일선상으로 이동")
    void moveCrossOrSameRowOrColMoveQueen(Position from, Position to) {
        Queen queen = new Queen(Color.BLACK);

        assertThat(queen.identifyMovementCondition(from, to))
                .isEqualTo(BaseMovementCondition.MUST_OBSTACLE_FREE);
    }

    private static Stream<Arguments> provideValidMoveQueen() {
        return Stream.of(
                Arguments.of(new Position(D, EIGHT), new Position(D, FOUR)),
                Arguments.of(new Position(D, EIGHT), new Position(H, EIGHT)),
                Arguments.of(new Position(D, EIGHT), new Position(A, EIGHT)),
                Arguments.of(new Position(D, EIGHT), new Position(F, SIX)),
                Arguments.of(new Position(D, EIGHT), new Position(A, FIVE)),
                Arguments.of(new Position(D, ONE), new Position(D, FOUR)),
                Arguments.of(new Position(D, ONE), new Position(F, THREE)),
                Arguments.of(new Position(D, ONE), new Position(A, FOUR))
        );
    }
}