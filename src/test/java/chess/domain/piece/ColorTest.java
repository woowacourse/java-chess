package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ColorTest {
    @Test
    @DisplayName("다음 색상으로 바뀌는지 테스트")
    void next() {
        
        // given
        Color color = Color.BLACK;
        
        assertThat(color.next()).isEqualTo(Color.WHITE);
    
        color = Color.WHITE;
    
        assertThat(color.next()).isEqualTo(Color.BLACK);
        
        assertThatIllegalStateException().isThrownBy(Color.BLANK::next);
    }
}