package chess.domain.piece;

import chess.domain.Chess;
import chess.domain.board.Board;
import chess.domain.position.MovePosition;
import chess.domain.position.Position;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RookTest {
    
    private final Rook rook = Rook.WHITE_INSTANCE;
    private final Position sourcePosition = Position.from("a1");
    private Board board;
    
    @BeforeEach
    void setUp() {
        board = Chess.createWithEmptyBoard()
                     .start()
                     .getBoard();
    }
    
    @Test
    @DisplayName("직선 1칸 이동 검사")
    void moveLinearOneStep() {
        
        // given
        final Position targetPosition = Position.from("a2");
        final Board newBoard = BoardUtils.put(board, targetPosition, Blank.INSTANCE);
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> rook.checkToMoveToTargetPosition(movePosition, newBoard);
        
        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("직선 여러 칸 이동 검사")
    void moveLinearMultiStep() {
        
        // given
        final Board newBoard = BoardUtils.put(board, Position.from("a2"), Blank.INSTANCE);
        final Position targetPosition = Position.from("a4");
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> rook.checkToMoveToTargetPosition(movePosition, newBoard);
        
        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("현재 위치에서 갈 수 없는 칸으로 이동하려 할 경우 예외 발생")
    void move_TryToMoveWhereCannotMove_ExceptionThrown() {
        
        // given
        final Position targetPosition = Position.from("b2");
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> rook.checkToMoveToTargetPosition(movePosition, board);
        
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("기물이 이동할 수 없는 위치입니다.");
    }
    
    @Test
    @DisplayName("이동 경로 사이에 기물이 있을 경우 예외 발생")
    void move_ObstacleIsInPath_ExceptionThrown() {
        
        // given
        final Position targetPosition = Position.from("a3");
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> rook.checkToMoveToTargetPosition(movePosition, board);
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("이동하는 경로 사이에 기물이 있습니다.");
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
