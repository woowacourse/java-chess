package chess.domain.piece;

import chess.game.Board;
import chess.game.InitializedChess;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RookTest {
    
    private final Rook rook = new Rook(Color.WHITE);
    private final Position sourcePosition = Position.of("a1");
    private final Board board = InitializedChess.create()
                                                .getBoard();
    
    @Test
    @DisplayName("직선 1칸 이동 검사")
    void moveLinearOneStep() {
        
        // given
        final Position targetPosition = Position.of("a2");
        final Board newBoard = BoardUtils.put(board, targetPosition, new Blank());
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> rook.move(sourcePosition, targetPosition, newBoard);
        
        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("직선 여러 칸 이동 검사")
    void moveLinearMultiStep() {
        
        // given
        final Board newBoard = BoardUtils.put(board, Position.of("a2"), new Blank());
        final Position targetPosition = Position.of("a4");
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> rook.move(sourcePosition, targetPosition, newBoard);
        
        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("현재 위치에서 갈 수 없는 칸으로 이동하려 할 경우 예외 발생")
    void move_TryToMoveWhereCannotMove_ExceptionThrown() {
        
        // given
        final Position targetPosition = Position.of("b2");
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> rook.move(sourcePosition, targetPosition, board);
        
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("기물이 이동할 수 없는 위치입니다.");
    }
    
    @Test
    @DisplayName("이동 경로 사이에 기물이 있을 경우 예외 발생")
    void move_ObstacleIsInPath_ExceptionThrown() {
        
        // given
        final Position targetPosition = Position.of("a3");
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> rook.move(sourcePosition, targetPosition, board);
        
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("이동하는 경로 사이에 기물이 있습니다.");
    }
    
    @Test
    @DisplayName("타겟 위치에 이미 기물이 있을 경우 예외 발생")
    void move_PieceAlreadyExistsAtTarget_ExceptionThrown() {
        
        // given
        final Position targetPosition = Position.of("a2");
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> rook.move(sourcePosition, targetPosition, board);
        
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("타겟 위치에 이미 기물이 있습니다.");
    }
    
    @Test
    @DisplayName("점수 반환 테스트")
    void score() {
        
        // when
        final double score = rook.getScore();
        
        // then
        assertThat(score).isEqualTo(5);
    }
}