package chess.domain.piece;

import chess.game.Board;
import chess.game.InitializedChess;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BishopTest {
    
    private final Bishop bishop = new Bishop(Color.WHITE);
    private final Position sourcePosition = Position.of("c1");
    private final Board board = InitializedChess.create()
                                                .getBoard();
    
    @Test
    @DisplayName("대각선 1칸 이동 검사")
    void moveOneStep() {
        
        // given
        final Position targetPosition = Position.of("b2");
        BoardUtils.put(board, targetPosition, new Blank());
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> bishop.move(sourcePosition, targetPosition, board);
        
        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("대각선 여러 칸 이동 검사")
    void moveMultiStep() {
        
        // given
        final Position removePosition = Position.of("b2");
        BoardUtils.put(board, removePosition, new Blank());
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> bishop.move(sourcePosition, Position.of("a3"), board);
        
        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("현재 위치에서 갈 수 없는 칸으로 이동하려 할 경우 예외 발생")
    void move_TryToMoveWhereCannotMove_ExceptionThrown() {
        
        // given
        final Position targetPosition = Position.of("b1");
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> bishop.move(sourcePosition, targetPosition, board);
        
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("기물이 이동할 수 없는 위치입니다.");
    }
    
    @Test
    @DisplayName("점수 반환 테스트")
    void scoreTest() {
        
        // when
        final double score = bishop.getScore();
        
        // then
        assertThat(score).isEqualTo(3);
    }
}