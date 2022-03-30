package chess.piece;

import static chess.position.File.A;
import static chess.position.File.B;
import static chess.position.File.D;
import static chess.position.File.E;
import static chess.position.Rank.EIGHT;
import static chess.position.Rank.FIVE;
import static chess.position.Rank.FOUR;
import static chess.position.Rank.ONE;
import static chess.position.Rank.SEVEN;
import static chess.position.Rank.SIX;
import static chess.position.Rank.THREE;
import static chess.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import chess.piece.movementcondition.BaseMovementCondition;
import chess.piece.movementcondition.MovementConditions;
import chess.position.Position;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {

    @ParameterizedTest
    @MethodSource("provideFirstMoveForwardPawn")
    @DisplayName("폰을 처음에 앞으로 한칸 또는 두칸을 움직일 수 있다.")
    void movePawn(Color color, Position from, Position to) {
        Pawn pawn = new Pawn(color);

        assertThat(pawn.identifyMovementCondition(from, to))
                .isEqualTo(new MovementConditions(
                        Set.of(BaseMovementCondition.MUST_EMPTY_DESTINATION,
                                BaseMovementCondition.MUST_OBSTACLE_FREE)));
    }

    private static Stream<Arguments> provideFirstMoveForwardPawn() {
        return Stream.of(
                Arguments.of(Color.BLACK, new Position(A, SEVEN), new Position(A, SIX)),
                Arguments.of(Color.BLACK, new Position(A, SEVEN), new Position(A, FIVE)),
                Arguments.of(Color.WHITE, new Position(A, TWO), new Position(A, THREE)),
                Arguments.of(Color.WHITE, new Position(A, TWO), new Position(A, FOUR))
        );
    }

    @Test
    @DisplayName("폰은 처음에는 3칸 이상 이동 시 예외가 발생한다.")
    void throwExceptionMovePawnOverTwoSpaceWhenFirstMove() {
        Pawn pawn = new Pawn(Color.BLACK);

        assertThat(pawn.identifyMovementCondition(new Position(A, SEVEN), new Position(A, FOUR)))
                .isEqualTo(BaseMovementCondition.IMPOSSIBLE);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidMoveForwardPawn")
    @DisplayName("폰이 처음 움직인 이후 부터는 두칸 이상 이동시 예외 발생")
    void throwExceptionMovePawnOverOneSpaceAfterFirstMove(Color color, Position from, Position to) {
        Pawn pawn = new Pawn(color);

        assertThat(pawn.identifyMovementCondition(from, to))
                .isEqualTo(BaseMovementCondition.IMPOSSIBLE);
    }

    private static Stream<Arguments> provideInvalidMoveForwardPawn() {
        return Stream.of(
                Arguments.of(Color.BLACK, new Position(A, SIX), new Position(A, FOUR)),
                Arguments.of(Color.WHITE, new Position(A, THREE), new Position(A, FIVE))
        );
    }

    @ParameterizedTest
    @MethodSource("provideMoveBackwardPawn")
    @DisplayName("폰은 뒤로 움직일 경우 예외가 발생한다.")
    void throwExceptionMovePawnBackward(Color color, Position from, Position to) {
        Pawn pawn = new Pawn(color);

        assertThat(pawn.identifyMovementCondition(from, to))
                .isEqualTo(BaseMovementCondition.IMPOSSIBLE);
    }

    private static Stream<Arguments> provideMoveBackwardPawn() {
        return Stream.of(
                Arguments.of(Color.BLACK, new Position(A, SEVEN), new Position(A, EIGHT)),
                Arguments.of(Color.WHITE, new Position(A, TWO), new Position(A, ONE))
        );
    }

    @ParameterizedTest
    @MethodSource("provideMoveSidePawn")
    @DisplayName("폰이 양옆으로 움직이려고 할 경우 예외 발생")
    void throwExceptionPawnMoveSide(Position from, Position to) {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThat(pawn.identifyMovementCondition(from, to))
                .isEqualTo(BaseMovementCondition.IMPOSSIBLE);
    }

    private static Stream<Arguments> provideMoveSidePawn() {
        return Stream.of(
                Arguments.of(new Position(A, TWO), new Position(B, TWO)),
                Arguments.of(new Position(B, TWO), new Position(A, TWO))
        );
    }

    @Test
    @DisplayName("폰은 대각선으로 기물을 포획할 때만 이동할 수 있다.")
    void throwExceptionWhenPawnMoveToNotHasTargetPieceDiagonalPosition() {
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.identifyMovementCondition(new Position(D, FOUR), new Position(E, FIVE)))
                .isEqualTo(BaseMovementCondition.MUST_CAPTURE_PIECE);
    }
}