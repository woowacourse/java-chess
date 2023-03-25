package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.NONE;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ColorTest {

    @DisplayName("흑을 색상변경하면 백이된다")
    @Test
    void Black_change_White() {
        assertThat(BLACK.change()).isSameAs(WHITE);
    }

    @DisplayName("백을 색상변경하면 흑이된다")
    @Test
    void White_change_Black() {
        assertThat(WHITE.change()).isSameAs(BLACK);
    }

    @DisplayName("없는 색깔을 색상변경 할 수 없다")
    @Test
    void None_change_throws() {
        assertThatThrownBy(() -> NONE.change())
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("없는 색깔입니다.");
    }
}
