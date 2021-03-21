package domain.piece;

import domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ColorTest {
    @DisplayName("검은색 말은 true로 생성한다.")
    @Test
    void black_color_generate() {
        Color black = Color.of(true);
        assertThat(black.getValue()).isTrue();
    }

    @DisplayName("흰색 말은 true로 생성한다.")
    @Test
    void white_color_generate() {
        Color black = Color.of(false);
        assertThat(black.getValue()).isFalse();
    }
}