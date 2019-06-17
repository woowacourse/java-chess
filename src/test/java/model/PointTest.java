package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PointTest {

    @Test
    void 동등_비교_equals_테스트() {
        assertThat(Point.valueOf("a1")).isEqualTo(Point.valueOf("a1"));
    }
}
