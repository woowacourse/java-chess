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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.position.Position;
import java.util.List;
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
        Pawn pawn = new Pawn(color, from);

        assertThat(pawn.transfer(to))
                .isEqualTo(new Pawn(color, to));
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
        Pawn pawn = new Pawn(Color.BLACK, new Position(A, SEVEN));

        assertThatThrownBy(() -> pawn.transfer(new Position(A, FOUR)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidMoveForwardPawn")
    @DisplayName("폰이 처음 움직인 이후 부터는 두칸 이상 이동시 예외 발생")
    void throwExceptionMovePawnOverOneSpaceAfterFirstMove(Color color, Position from, Position to) {
        Pawn pawn = new Pawn(color, from);

        assertThatThrownBy(() -> pawn.transfer(to))
                .isInstanceOf(IllegalArgumentException.class);
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
        Pawn pawn = new Pawn(color, from);

        assertThatThrownBy(() -> pawn.transfer(to))
                .isInstanceOf(IllegalArgumentException.class);
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
        Pawn pawn = new Pawn(Color.WHITE, from);

        assertThatThrownBy(() -> pawn.transfer(to))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> provideMoveSidePawn() {
        return Stream.of(
//                Arguments.of(new Position(A, TWO), new Position(B, THREE)),
                Arguments.of(new Position(A, TWO), new Position(B, TWO)),
                Arguments.of(new Position(B, TWO), new Position(A, TWO))
        );
    }

    @Test
    @DisplayName("폰이 이동하는 위치에 기물이 있으면 예외 발생")
    void throwExceptionMovePawnAlreadyExistPiecePosition() {
        Pawn pickedPawn = new Pawn(Color.WHITE, new Position(D, FOUR));
        assertThat(pickedPawn.isUncapturablePosition(new Position(D, FIVE))).isTrue();
    }

    @Test
    @DisplayName("폰은 대각선에 기물이 없으면 이동할 수 없다.")
    void throwExceptionWhenPawnMoveToNotHasTargetPieceDiagonalPosition() {
        Pawn pickedPawn = new Pawn(Color.WHITE, new Position(D, FOUR));

        assertThat(pickedPawn.isCapturablePosition(new Position(E, FIVE))).isTrue();
    }

    @Test
    @DisplayName("폰은 대각선에 위치한 기물을 제거할 수 있다.")
    void removeTargetPieceByPawn() {
        Pawn pickedPawn = new Pawn(Color.WHITE, new Position(D, FOUR));
        Pawn targetPawn = new Pawn(Color.BLACK, new Position(E, FIVE));

        assertThat(pickedPawn.transfer(new Position(E, FIVE)))
                .isEqualTo(new Pawn(Color.WHITE, new Position(E, FIVE)));
    }
}