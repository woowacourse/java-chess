package chess.domain.piece;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ColorTest {
    @Test
    void moveUnit() {
        Color color = Color.BLACK;
        assertThat(color.moveUnit()).isEqualTo(-1);
    }
}