package chess.domain.piece;

import chess.game.Board;
import chess.game.InitializedChess;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class KnightTest {
    private final Knight knight = new Knight(Color.WHITE);
    private final Position sourcePosition = Position.of("b1");
    private final Board board = InitializedChess.create()
                                                .getBoard();
    
    @Test
    @DisplayName("이동 검사")
    void moveTest() {
        
        // given
        final Position targetPosition = Position.of("a3");
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> knight.move(sourcePosition, targetPosition, board);
        
        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("타겟 위치에 이미 기물이 있을 경우 예외 발생")
    void move_PieceAlreadyExistsAtTarget_ExceptionThrown() {
        
        // given
        final Position targetPosition = Position.of("d2");
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> knight.move(sourcePosition, targetPosition, board);
        
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("타겟 위치에 이미 기물이 있습니다.");
    }
    
    
    @Test
    @DisplayName("현재 위치에서 갈 수 없는 칸으로 이동하려 할 경우 예외 발생")
    void move_TryToMoveWhereCannotMove_ExceptionThrown() {
        
        // given
        final Position targetPosition = Position.of("a2");
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> knight.move(sourcePosition, targetPosition, board);
        
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
    
    @Test
    @DisplayName("점수 반환 테스트")
    void scoreTest() {
        
        // when
        final double score = knight.getScore();
        
        // then
        assertThat(score).isEqualTo(2.5);
    }
}