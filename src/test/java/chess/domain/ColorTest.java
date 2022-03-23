package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ColorTest {

    @Test
    @DisplayName("진영을 확인한다.")
    void 진영을_확인한다() {
        Color color = Color.BLACK;

        assertThat(color.isBlack()).isTrue();
    }
}
