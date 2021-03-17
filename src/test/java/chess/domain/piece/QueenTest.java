package chess.domain.piece;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class QueenTest {
    
    private Queen queen;
    
    @BeforeEach
    void setUp() {
        Position position = Position.of(0, 3);
        
        queen = new Queen(Color.BLACK, position);
    }
    
    @Test
    @DisplayName("이동검사")
    void move() {
        Position position = Position.of(7, 3);
        ThrowableAssert.ThrowingCallable callable = () -> queen.move(position);
    
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("이동 에러 검사")
    void validate() {
        Position position = Position.of(7, 2);
        
        ThrowableAssert.ThrowingCallable callable = () -> queen.move(position);
    
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
    
    @Test
    @DisplayName("점수 반환 테스트")
    void score() {
        assertThat(queen.getScore()).isEqualTo(9);
    }
}
