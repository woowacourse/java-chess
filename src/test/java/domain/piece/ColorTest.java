package domain.piece;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ColorTest {
    @Test
    void moveUnit() {
        Color color = Color.BLACK;
        assertThat(color.moveUnit()).isEqualTo(-1);
    }
}