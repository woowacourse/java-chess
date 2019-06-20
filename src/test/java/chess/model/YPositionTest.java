package chess.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class YPositionTest {
    @Test
    void Y_위치_1_미만_테스트() {
        assertThrows(IllegalYPositionException.class, () -> YPosition.valueOf(0));
    }

    @Test
    void Y_위치_8_초과_테스트() {
        assertThrows(IllegalYPositionException.class, () -> YPosition.valueOf(9));
    }

    @Test
    void Y_equals_테스트() {
        assertThat(YPosition.valueOf(1)).isEqualTo(YPosition.valueOf(1));
    }

    @Test
    void source_target의_x값_차의_절대값_비교() {
        YPosition source = YPosition.valueOf(1);
        YPosition target = YPosition.valueOf(7);

        int actual = source.calculateYsDiff(target);
        assertThat(actual).isEqualTo(6);
    }
}
