package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;

class ColorTest {

    @Test
    @DisplayName("흰색을 뒤집으면 검은색이 된다.")
    void toggleWhite() {
        Color color = Color.WHITE;

        Color toggled = color.toggle();

        assertThat(toggled).isEqualTo(Color.BLACK);
    }

    @Test
    @DisplayName("검은색을 뒤집으면 흰색이 된다.")
    void toggleBlack() {
        Color color = Color.BLACK;

        Color toggled = color.toggle();

        assertThat(toggled).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("None은 뒤집을 수 없다.")
    void throwsExceptionWithNoneToggle() {
        Color color = Color.NONE;

        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(color::toggle);
    }
}
