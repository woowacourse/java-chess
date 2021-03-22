package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ColorTest {
    
    @Test
    @DisplayName("WHITE가 BLACK으로 변하는지 테스트")
    void next_WhiteChangeToBlack() {
        
        // given
        Color color = Color.BLACK;
        
        // when
        final Color next = color.next();
        
        // then
        assertThat(next).isEqualTo(Color.WHITE);
    }
    
    @Test
    @DisplayName("BLACK이 WHITE로 변하는지 테스트")
    void next_BlackChangeToWhite() {
        
        // given
        Color color = Color.WHITE;
        
        // when
        final Color next = color.next();
        
        // then
        assertThat(next).isEqualTo(Color.BLACK);
    }
}