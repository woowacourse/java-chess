package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ColorTest {

    @DisplayName("흑을 색상변경하면 백이된다")
    @Test
    void Black_change_White() {
        assertThat(Color.BLACK.change()).isSameAs(Color.WHITE);
    }

    @DisplayName("백을 색상변경하면 흑이된다")
    @Test
    void White_change_Black() {
        assertThat(Color.WHITE.change()).isSameAs(Color.BLACK);
    }
}
