package chess.domain.piece;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RookTest {
    private Rook rook;
    
    @BeforeEach
    void setUp() {
        Position position = Position.of(0, 0);
        
        rook = new Rook(Color.BLACK, position);
    }
    
    @Test
    @DisplayName("이동검사")
    void move() {
        Position position = Position.of(2, 0);
        
        ThrowableAssert.ThrowingCallable callable = () -> rook.move(position);
        
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("이동 에러 검사")
    void validate() {
        Position position = Position.of(2, 1);
        
        ThrowableAssert.ThrowingCallable callable = () -> rook.move(position);
        
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
    
    @Test
    @DisplayName("점수 반환 테스트")
    void score() {
        assertThat(rook.getScore()).isEqualTo(5);
    }
}