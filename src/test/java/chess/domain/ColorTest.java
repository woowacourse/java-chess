package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ColorTest {

    @Test
    @DisplayName("Color에 흑,백이 있는지 확인한다.")
    void containBlackAndWhite() {
        assertThat(Color.values()).containsOnly(Color.BLACK, Color.WHITE);
    }
}
