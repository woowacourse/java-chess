package chess.domain.piece;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.board.Board;
import chess.domain.chess.Chess;
import chess.domain.position.MovePosition;
import chess.domain.position.Position;

class PawnTest {
    private final Pawn pawn = Pawn.WHITE_INSTANCE;
    private final Position sourcePosition = Position.from("a2");
    private final Board board = Chess.createWithEmptyBoard()
                                     .start()
                                     .getBoard();

    @Test
    @DisplayName("1칸 전진 테스트")
    void moveLinearOneStep() {

        // given
        Position targetPosition = Position.from("a3");
        MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);

        // when
        ThrowableAssert.ThrowingCallable callable =
                () -> pawn.checkToMoveToTargetPosition(movePosition, board);

        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("2칸 전진 테스트")
    void moveLinearTwoStep() {

        // given
        Position targetPosition = Position.from("a4");
        MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);

        // when
        ThrowableAssert.ThrowingCallable callable =
                () -> pawn.checkToMoveToTargetPosition(movePosition, board);

        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("3칸 이상 전진 시 예외 발생")
    void moveLinearMultiStep_OverTwoStep_ExceptionThrown() {

        // given
        Position targetPosition = Position.from("a5");
        MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);

        // when
        ThrowableAssert.ThrowingCallable callable =
                () -> pawn.checkToMoveToTargetPosition(movePosition, board);

        // then
        assertThatIllegalArgumentException().isThrownBy(callable);
    }

    @Test
    @DisplayName("1칸 대각선에 있는 상대방 기물 잡기 테스트")
    void moveDiagonalOneStep() {

        // given
        Position targetPosition = Position.from("b3");
        Board newBoard = BoardUtils.put(board, targetPosition, Pawn.WHITE_INSTANCE);
        MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);

        // when
        ThrowableAssert.ThrowingCallable callable =
                () -> pawn.checkToMoveToTargetPosition(movePosition, newBoard);

        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("타겟 위치에 이미 기물이 있을 경우 예외 발생")
    void move_PieceAlreadyExistsAtTarget_ExceptionThrown() {

        // given
        Position targetPosition = Position.from("a3");
        Board newBoard = BoardUtils.put(board, targetPosition, Pawn.WHITE_INSTANCE);
        MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);

        // when
        ThrowableAssert.ThrowingCallable callable =
                () -> pawn.checkToMoveToTargetPosition(movePosition, newBoard);

        // then
        assertThatIllegalArgumentException().isThrownBy(callable);
    }

    @Test
    @DisplayName("첫 이동이 아닌데 2칸을 전진할 경우 예외 발생")
    void moveTwoStep_IfPawnIsNotFirstMovement_ExceptionThrown() {

        // given
        Position newSourcePosition = Position.from("c3");
        Board newBoard = BoardUtils.put(board, newSourcePosition, Pawn.WHITE_INSTANCE);

        Position targetPosition = Position.from("c5");
        MovePosition movePosition = new MovePosition(newSourcePosition, targetPosition);

        // when
        ThrowableAssert.ThrowingCallable callable =
                () -> pawn.checkToMoveToTargetPosition(movePosition, newBoard);

        // then
        assertThatIllegalArgumentException().isThrownBy(callable);
    }

    @Test
    @DisplayName("1칸 대각선 이동시 타겟 위치가 블랭크일 경우 이동 불가")
    void moveDiagonal_IfTargetIsBlank_ExceptionThrown() {

        // given
        final Position targetPosition = Position.from("b3");
        MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);

        // when
        ThrowableAssert.ThrowingCallable callable =
                () -> pawn.checkToMoveToTargetPosition(movePosition, board);

        // then
        assertThatIllegalArgumentException().isThrownBy(callable);
    }

    @Test
    @DisplayName("2칸 이동 시 경로 중간에 기물이 있을 경우 예외 발생")
    void move_ObstacleIsInPath_ExceptionThrown() {

        // given
        Position blackPawnPosition = Position.from("a3");
        Board newBoard = BoardUtils.put(board, blackPawnPosition, Pawn.WHITE_INSTANCE);

        Position targetPosition = Position.from("a4");
        MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);

        // when
        ThrowableAssert.ThrowingCallable callable =
                () -> pawn.checkToMoveToTargetPosition(movePosition, newBoard);

        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("이동하는 경로 사이에 기물이 있습니다.");
    }

    @Test
    @DisplayName("점수 반환 테스트")
    void scoreTest() {
        assertThat(pawn.getScore()).isEqualTo(1);
    }
}
