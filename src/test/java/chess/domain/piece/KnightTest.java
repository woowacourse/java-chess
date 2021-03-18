package chess.domain.piece;

import chess.board.Board;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class KnightTest {
    private Knight knight;
    
    private List<List<Piece>> pieces;
    
    @BeforeEach
    void setUp() {
        Position position = Position.of(0, 1);
    
        knight = new Knight(Color.BLACK, position);
        
        Board board = new Board();
        board.init();
        
        pieces = board.getBoard();
    }
    
    @Test
    @DisplayName("이동검사")
    void move() {
        Position position = Position.of(2, 0);
        
        ThrowableAssert.ThrowingCallable callable = () -> knight.move(position, pieces);
        
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("이동 에러 검사")
    void validate() {
        Position position = Position.of(2, 1);
        
        ThrowableAssert.ThrowingCallable callable = () -> knight.move(position, pieces);
        
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
    
    @Test
    @DisplayName("점수 반환 테스트")
    void score() {
        assertThat(knight.getScore()).isEqualTo(2.5);
    }
}