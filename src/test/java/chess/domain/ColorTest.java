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
    
    @Test
    @DisplayName("검은색인지 테스트")
    void isBlackTest() {
        
        // given
        Color color = Color.BLACK;
        
        // when
        boolean isBlack = color.isBlack();
        
        // then
        assertThat(isBlack).isTrue();
    }
    
    @Test
    @DisplayName("검은색이 아닌지 테스트")
    void isBlackTest_False() {
    
        // given
        Color color = Color.BLACK;
    
        // when
        boolean isBlack = color.isWhite();
    
        // then
        assertThat(isBlack).isFalse();
    }
    
    @Test
    @DisplayName("하얀색인지 테스트")
    void isWhiteTest() {
        
        // given
        Color color = Color.WHITE;
        
        // when
        boolean isWhite = color.isWhite();
        
        // then
        assertThat(isWhite).isTrue();
    }
    
    @Test
    @DisplayName("하얀색이 아닌지 테스트")
    void isWhiteTest_False() {
        
        // given
        Color color = Color.BLACK;
        
        // when
        boolean isWhite = color.isWhite();
        
        // then
        assertThat(isWhite).isFalse();
    }
}
