package chess.domain.piece;

import chess.board.Board;
import chess.view.OutputView;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QueenTest {
    
    private Queen queen;
    
    private List<List<Piece>> pieces;
    
    @BeforeEach
    void setUp() {
        Position position = Position.of("d1");
    
        queen = new Queen(Color.BLACK, position);
        
        Board board = new Board();
        board.init();
        
        pieces = board.getBoard();
    
        pieces.get(1).set(2, new Blank(Color.BLANK, Position.of("c2")));
    }
    
    @Test
    @DisplayName("이동검사")
    void move() {
        Position position = Position.of("a4");
        
        ThrowableAssert.ThrowingCallable callable = () -> queen.move(position, pieces);
    
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("이동 에러 검사")
    void validate() {
        Position position = Position.of(7, 2);
        
        ThrowableAssert.ThrowingCallable callable = () -> queen.move(position, pieces);
    
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
    
    @Test
    @DisplayName("점수 반환 테스트")
    void score() {
        assertThat(queen.getScore()).isEqualTo(9);
    }
}
