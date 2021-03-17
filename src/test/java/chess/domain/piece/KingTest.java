package chess.domain.piece;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class KingTest {
    private King king;
    
    @BeforeEach
    void setUp() {
        Position position = Position.of(0, 4);
        
        king = new King(Color.BLACK, position);
    }
    
    @Test
    @DisplayName("이동검사")
    void move() {
        Position position = Position.of(1, 4);
        
        ThrowableAssert.ThrowingCallable callable = () -> king.move(position);
        
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("이동 에러 검사")
    void validate() {
        Position position = Position.of(2, 4);
        
        ThrowableAssert.ThrowingCallable callable = () -> king.move(position);
        
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
    
    @Test
    @DisplayName("점수 반환 테스트")
    void score() {
        assertThat(king.getScore()).isEqualTo(0);
    }
}