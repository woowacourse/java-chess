package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CoordinateTest {
    @Test
    @DisplayName("좌표가 유효한 숫자 범위 내에 있다.")
    void isValidTest() {
        Coordinate coordinateOfNegative = Coordinate.of(-1);
        Coordinate coordinate0 = Coordinate.of(0);
        Coordinate coordinate7 = Coordinate.of(7);
        Coordinate coordinate8 = Coordinate.of(8);

        assertThat(coordinateOfNegative.isValid()).isFalse();
        assertThat(coordinate0.isValid()).isTrue();
        assertThat(coordinate7.isValid()).isTrue();
        assertThat(coordinate8.isValid()).isFalse();
    }
}