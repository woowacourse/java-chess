package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ColorTest {
    @Test
    @DisplayName("블랙의 단위벡터가 -1로 나오는지 확인")
    void moveBlackUnit() {
        Color color = Color.BLACK;
        assertThat(color.moveUnit()).isEqualTo(-1);
    }

    @Test
    @DisplayName("화이트의 단위벡터가 1로 나오는지 확인")
    void moveWhiteUnit() {
        Color color = Color.WHITE;
        assertThat(color.moveUnit()).isEqualTo(1);
    }
}