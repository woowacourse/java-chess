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

public class QueenTest {
    
    private final Queen queen = new Queen(Color.WHITE);
    private final Position sourcePosition = Position.of("d1");
    private final Board board = Chess.createWithInitializedBoard()
                                     .getBoard();
    
    @Test
    @DisplayName("대각선 1칸 이동 검사")
    void moveDiagonalOneStep() {
        
        // given
        final Position targetPosition = Position.of("c2");
        final Board newBoard = BoardUtils.put(board, targetPosition, new Blank());
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> queen.checkToMoveToTargetPosition(movePosition, newBoard);
        
        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("대각선 여러 칸 이동 검사")
    void moveDiagonalMultiStep() {
        
        // given
        final Board newBoard = BoardUtils.put(board, Position.of("c2"), new Blank());
        final Position targetPosition = Position.of("a4");
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> queen.checkToMoveToTargetPosition(movePosition, newBoard);
        
        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    
    @Test
    @DisplayName("직선 1칸 이동 검사")
    void moveLinearOneStep() {
        
        // given
        final Position targetPosition = Position.of("d2");
        final Board newBoard = BoardUtils.put(board, targetPosition, new Blank());
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> queen.checkToMoveToTargetPosition(movePosition, newBoard);
        
        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("직선 여러 칸 이동 검사")
    void moveLinearMultiStep() {
        
        // given
        final Board newBoard = BoardUtils.put(board, Position.of("d2"), new Blank());
        final Position targetPosition = Position.of("d4");
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> queen.checkToMoveToTargetPosition(movePosition, newBoard);
        
        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("현재 위치에서 갈 수 없는 칸으로 이동하려 할 경우 예외 발생")
    void move_TryToMoveWhereCannotMove_ExceptionThrown() {
        
        // given
        final Position targetPosition = Position.of("b2");
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> queen.checkToMoveToTargetPosition(movePosition, board);
        
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
    
    @Test
    @DisplayName("이동 경로 사이에 기물이 있을 경우 예외 발생")
    void move_ObstacleIsInPath_ExceptionThrown() {
        
        // given
        final Position targetPosition = Position.of("d3");
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> queen.checkToMoveToTargetPosition(movePosition, board);
        
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("이동하는 경로 사이에 기물이 있습니다.");
    }
    
    @Test
    @DisplayName("점수 반환 테스트")
    void scoreTest() {
        
        // when
        final double score = queen.getScore();
        
        // then
        assertThat(score).isEqualTo(9);
    }
}
