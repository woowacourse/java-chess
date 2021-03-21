package chess.domain.piece;

import chess.domain.Color;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

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
    @DisplayName("BLANK에서 next를 호출할 경우 예외 발생")
    void next_IfBlankInvokeNext_ExceptionThrown() {
        
        // given
        Color color = Color.BLANK;
        
        // when
        ThrowableAssert.ThrowingCallable callable = color::next;
        
        // then
        assertThatIllegalStateException().isThrownBy(callable)
                                         .withMessage("Turn이 BLANK로 설정되어 있습니다. 게임을 다시 시작해주세요.");
    }
}