package chess.domain.piece;

import chess.board.Board;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PawnTest {
    private Pawn pawn;
    
    private List<List<Piece>> pieces;
    
    @BeforeEach
    void setUp() {
        Position position = Position.of(1, 1);
    
        pawn = new Pawn(Color.WHITE, position);
        
        Board board = new Board();
        board.init();
        
        pieces = board.getBoard();
    }
    
    @Test
    @DisplayName("이동검사")
    void move() {
        Position position = Position.of(2, 1);
        
        ThrowableAssert.ThrowingCallable callable = () -> pawn.move(position, pieces);
        
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("이동 에러 검사")
    void validate() {
        Position position = Position.of(2, 4);
        
        ThrowableAssert.ThrowingCallable callable = () -> pawn.move(position, pieces);
        
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
    
    @Test
    @DisplayName("점수 반환 테스트")
    void score() {
        assertThat(pawn.getScore()).isEqualTo(1);
    }
}