package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ColorTest {

    @Test
    @DisplayName("검은색 기물이면 대문자를 출력하는지")
    void upperCaseOnBlack(){
        Color color = Color.BLACK;
        assertThat(color.correctSignature("a")).isEqualTo("A");
    }

    @Test
    @DisplayName("흰색 기물이면 소문자를 출력하는지")
    void lowerCaseOnWhite(){
        Color color = Color.WHITE;
        assertThat(color.correctSignature("a")).isEqualTo("a");
    }
}
