package chess.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class XPositionTest {
    @Test
    void X_위치_1_미만_테스트() {
        assertThrows(IllegalXPositionException.class, () -> XPosition.valueOf(0));
    }

    @Test
    void X_위치_8_초과_테스트() {
        assertThrows(IllegalXPositionException.class, () -> XPosition.valueOf(9));
    }

    @Test
    void X_equals_테스트() {
        assertThat(XPosition.valueOf(1)).isEqualTo(XPosition.valueOf(1));
    }

    @Test
    void source_target의_x값_차의_절대값_비교() {
        XPosition source = XPosition.valueOf(3);
        XPosition target = XPosition.valueOf(6);

        int actual = source.calculateXsDiff(target);
        assertThat(actual).isEqualTo(3);
    }
}
