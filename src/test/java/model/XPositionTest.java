package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class XPositionTest {
    @Test
    void 알파벳_범위_초과() {
        assertThrows(IllegalArgumentException.class, () -> XPosition.valueOf('i'));
    }

    @Test
    void 알파벳_범위_내() {
        XPosition.valueOf('a');
    }

    @Test
    void 동등비교_확인() {
        assertThat(XPosition.valueOf('a')).isEqualTo(XPosition.valueOf('a'));
    }

    @Test
    void 위치_값_확인() {
        assertThat(XPosition.valueOf('a').getValue()).isEqualTo(1);
    }
}
