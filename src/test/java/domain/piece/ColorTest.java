package domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ColorTest {

    @Test
    @DisplayName("흰색인 경우 검은색을 반환한다.")
    void opposite_White_Black() {
        Color white = Color.WHITE;

        Color actual = white.opposite();

        assertThat(actual).isEqualTo(Color.BLACK);
    }

    @Test
    @DisplayName("검은색인 경우 흰색을 반환한다.")
    void opposite_Black_White() {
        Color black = Color.BLACK;

        Color actual = black.opposite();

        assertThat(actual).isEqualTo(Color.WHITE);
    }
}
