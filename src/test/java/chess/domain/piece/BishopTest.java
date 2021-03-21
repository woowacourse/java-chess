package chess.domain.piece;

import chess.domain.Chess;
import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.position.MovePosition;
import chess.domain.position.Position;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BishopTest {
    
    private final Bishop bishop = new Bishop(Color.WHITE);
    private final Position sourcePosition = Position.of("c1");
    private Board board;
    
    @BeforeEach
    void setUp() {
        board = Chess.createWithInitializedBoard().getBoard();
    }
    
    @Test
    @DisplayName("대각선 1칸 이동 검사")
    void moveOneStep() {
        
        // given
        final Position targetPosition = Position.of("b2");
        BoardUtils.put(board, targetPosition, new Blank());
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
        final Position removePosition = Position.of("b2");
        BoardUtils.put(board, removePosition, new Blank());
        final MovePosition movePosition = new MovePosition(sourcePosition, Position.of("a3"));
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> bishop.checkToMoveToTargetPosition(movePosition, board);
        
        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("현재 위치에서 갈 수 없는 칸으로 이동하려 할 경우 예외 발생")
    void move_TryToMoveWhereCannotMove_ExceptionThrown() {
        
        // given
        final Position targetPosition = Position.of("b1");
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