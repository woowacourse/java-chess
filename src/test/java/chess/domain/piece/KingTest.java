package chess.domain.piece;

import chess.domain.game.Board;
import chess.domain.game.Chess;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class KingTest {
    
    private final King king = new King(Color.WHITE);
    private final Position sourcePosition = Position.of("e1");
    private final Board board = Chess.createWithInitializedBoard().getBoard();
    
    @Test
    @DisplayName("대각선 1칸 이동 검사")
    void moveDiagonalOneStep() {
        
        // given
        final Position targetPosition = Position.of("d2");
        final Board newBoard = BoardUtils.put(board, targetPosition, new Blank());
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> king.move(sourcePosition, targetPosition, newBoard);
        
        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("대각선 여러 칸 이동 시 예외 발생 검사")
    void moveDiagonalMultiStep() {
        
        // given
        final Board newBoard = BoardUtils.put(board, Position.of("d2"), new Blank());
        final Position targetPosition = Position.of("c3");
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> king.move(sourcePosition, targetPosition, newBoard);
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("기물이 이동할 수 없는 위치입니다.");
    }
    
    @Test
    @DisplayName("직선 1칸 이동 검사")
    void moveLinearOneStep() {
        
        // given
        final Position targetPosition = Position.of("e2");
        Board boardRemoveE2 = BoardUtils.put(this.board, targetPosition, new Blank());
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> king.move(sourcePosition, targetPosition, boardRemoveE2);
        
        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("직선 여러 칸 이동 시 예외 발생")
    void moveLinearMultiStep() {
        
        // given
        final Board newBoard = BoardUtils.put(board, Position.of("d2"), new Blank());
        final Position targetPosition = Position.of("d4");
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> king.move(sourcePosition, targetPosition, newBoard);
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("기물이 이동할 수 없는 위치입니다.");
    }
    
    @Test
    @DisplayName("타겟 위치에 이미 기물이 있을 경우 예외 발생")
    void move_PieceAlreadyExistsAtTarget_ExceptionThrown() {
        
        // given
        final Position targetPosition = Position.of("d2");
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> king.move(sourcePosition, targetPosition, board);
        
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("타겟 위치에 이미 기물이 있습니다.");
    }
    
    @Test
    @DisplayName("현재 위치에서 갈 수 없는 칸으로 이동하려 할 경우 예외 발생")
    void move_TryToMoveWhereCannotMove_ExceptionThrown() {
        
        // given
        final Position targetPosition = Position.of("e3");
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> king.move(sourcePosition, targetPosition, board);
        
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