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

class KingTest {

    private final King king = King.WHITE_INSTANCE;
    private final Position sourcePosition = Position.from("e1");
    private final Board board = Chess.createWithEmptyBoard()
                                     .start()
                                     .getBoard();

    @Test
    @DisplayName("대각선 1칸 이동 검사")
    void moveDiagonalOneStep() {

        // given
        final Position targetPosition = Position.from("d2");
        final Board newBoard = BoardUtils.put(board, targetPosition, Blank.INSTANCE);
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);

        // when
        ThrowableAssert.ThrowingCallable callable =
                () -> king.checkToMoveToTargetPosition(movePosition, newBoard);

        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("대각선 여러 칸 이동 시 예외 발생 검사")
    void moveDiagonalMultiStep() {

        // given
        final Board newBoard = BoardUtils.put(board, Position.from("d2"), Blank.INSTANCE);
        final Position targetPosition = Position.from("c3");
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);

        // when
        ThrowableAssert.ThrowingCallable callable =
                () -> king.checkToMoveToTargetPosition(movePosition, newBoard);

        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("기물이 이동할 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("직선 1칸 이동 검사")
    void moveLinearOneStep() {

        // given
        final Position targetPosition = Position.from("e2");
        Board newBoard = BoardUtils.put(this.board, targetPosition, Blank.INSTANCE);
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);

        // when
        ThrowableAssert.ThrowingCallable callable =
                () -> king.checkToMoveToTargetPosition(movePosition, newBoard);

        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("직선 여러 칸 이동 시 예외 발생")
    void moveLinearMultiStep() {

        // given
        final Board newBoard = BoardUtils.put(board, Position.from("d2"), Blank.INSTANCE);
        final Position targetPosition = Position.from("d4");
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);

        // when
        ThrowableAssert.ThrowingCallable callable =
                () -> king.checkToMoveToTargetPosition(movePosition, newBoard);

        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("기물이 이동할 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("현재 위치에서 갈 수 없는 칸으로 이동하려 할 경우 예외 발생")
    void move_TryToMoveWhereCannotMove_ExceptionThrown() {

        // given
        final Position targetPosition = Position.from("e3");
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);

        // when
        ThrowableAssert.ThrowingCallable callable =
                () -> king.checkToMoveToTargetPosition(movePosition, board);

        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("기물이 이동할 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("점수 반환 테스트")
    void scoreTest() {

        // when
        final double score = king.getScore();

        // then
        assertThat(score).isEqualTo(0);
    }
}
