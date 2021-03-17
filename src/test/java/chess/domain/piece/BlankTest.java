package chess.domain.piece;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BlankTest {
    private Blank blank;
    
    @BeforeEach
    void setUp() {
        Position position = Position.of(3, 0);
        
        blank = new Blank(Color.BLACK, position);
    }
    
    @Test
    @DisplayName("이동 에러 검사")
    void validate() {
        Position position = Position.of(3, 1);
        
        ThrowableAssert.ThrowingCallable callable = () -> blank.move(position);
        
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
    
    @Test
    @DisplayName("점수 반환 테스트")
    void score() {
        assertThat(blank.getScore()).isEqualTo(0);
    }
}