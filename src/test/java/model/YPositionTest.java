package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class YPositionTest {

    @Test
    void yPosition_생성_확인_1미만() {
        assertThrows(IllegalArgumentException.class, () -> {
            YPosition.valueOf(0);
        });
    }

    @Test
    void yPosition_생성_확인_8_초과() {
        assertThrows(IllegalArgumentException.class, () -> {
            YPosition.valueOf(9);
        });
    }

    @Test
    void yPosition_생성_확인_범위_내() {
        YPosition.valueOf(3);
    }

    @Test
    void 동등비교_작동_확인() {
        assertThat(YPosition.valueOf(1)).isEqualTo(YPosition.valueOf(1));
    }
}
