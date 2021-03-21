package chess.domain.piece;

import chess.domain.Chess;
import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.position.MovePosition;
import chess.domain.position.Position;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class KingTest {
    
    private final King king = new King(Color.WHITE);
    private final Position sourcePosition = Position.of("e1");
    private final Board board = Chess.createWithInitializedBoard()
                                     .getBoard();
    
    @Test
    @DisplayName("대각선 1칸 이동 검사")
    void moveDiagonalOneStep() {
        
        // given
        final Position targetPosition = Position.of("d2");
        final Board newBoard = BoardUtils.put(board, targetPosition, new Blank());
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> king.checkToMoveToTargetPosition(movePosition, newBoard);
        
        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("대각선 여러 칸 이동 시 예외 발생 검사")
    void moveDiagonalMultiStep() {
        
        // given
        final Board newBoard = BoardUtils.put(board, Position.of("d2"), new Blank());
        final Position targetPosition = Position.of("c3");
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> king.checkToMoveToTargetPosition(movePosition, newBoard);
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("기물이 이동할 수 없는 위치입니다.");
    }
    
    @Test
    @DisplayName("직선 1칸 이동 검사")
    void moveLinearOneStep() {
        
        // given
        final Position targetPosition = Position.of("e2");
        Board newBoard = BoardUtils.put(this.board, targetPosition, new Blank());
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> king.checkToMoveToTargetPosition(movePosition, newBoard);
        
        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("직선 여러 칸 이동 시 예외 발생")
    void moveLinearMultiStep() {
        
        // given
        final Board newBoard = BoardUtils.put(board, Position.of("d2"), new Blank());
        final Position targetPosition = Position.of("d4");
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> king.checkToMoveToTargetPosition(movePosition, newBoard);
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("기물이 이동할 수 없는 위치입니다.");
    }
    
    @Test
    @DisplayName("현재 위치에서 갈 수 없는 칸으로 이동하려 할 경우 예외 발생")
    void move_TryToMoveWhereCannotMove_ExceptionThrown() {
        
        // given
        final Position targetPosition = Position.of("e3");
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> king.checkToMoveToTargetPosition(movePosition, board);
        
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