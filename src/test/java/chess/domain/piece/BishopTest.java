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

class BishopTest {
    
    private final Bishop bishop = Bishop.WHITE_INSTANCE;
    private final Position sourcePosition = Position.from("c1");
    private Board board;
    
    @BeforeEach
    void setUp() {
        board = Chess.createWithEmptyBoard()
                     .start()
                     .getBoard();
    }
    
    @Test
    @DisplayName("대각선 1칸 이동 검사")
    void moveOneStep() {
        
        // given
        final Position targetPosition = Position.from("b2");
        BoardUtils.put(board, targetPosition, Blank.INSTANCE);
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> bishop.checkToMoveToTargetPosition(movePosition, board);
        
        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("대각선 여러 칸 이동 검사")
    void moveMultiStep() {
        
        // given
        final Position removePosition = Position.from("b2");
        BoardUtils.put(board, removePosition, Blank.INSTANCE);
        final MovePosition movePosition = new MovePosition(sourcePosition, Position.from("a3"));
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> bishop.checkToMoveToTargetPosition(movePosition, board);
        
        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("현재 위치에서 갈 수 없는 칸으로 이동하려 할 경우 예외 발생")
    void move_TryToMoveWhereCannotMove_ExceptionThrown() {
        
        // given
        final Position targetPosition = Position.from("b1");
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> bishop.checkToMoveToTargetPosition(movePosition, board);
        
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
