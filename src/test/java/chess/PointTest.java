package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PointTest {
    @DisplayName("좌표 생성")
    @Test
    void create() {
        assertDoesNotThrow(
                () -> Point.of('a', 8)
        );
        assertDoesNotThrow(
                () -> Point.of('h', 1)
        );
    }

    @DisplayName("범위 밖 좌표 생성 시 예외 처리")
    @Test
    void checkIndexOutOfRange() {
        assertThatThrownBy(() -> Point.of('i', 0)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Point.of('i', 8)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Point.of('h', 0)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Point.of('h', 9)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("인덱스로 좌표 생성")
    @Test
    void createWithIndex() {
        assertDoesNotThrow(
                () -> Point.valueOf(0, 7)
        );
        assertDoesNotThrow(
                () -> Point.valueOf(7, 0)
        );
    }

    @DisplayName("인덱스로 범위 밖 좌표 생성 시 예외 처리")
    @Test
    void checkValueOfRange() {
        assertThatThrownBy(() -> Point.valueOf(-1, -1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Point.valueOf(0, 8)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Point.valueOf(8, 0)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Point.valueOf(8, 8)).isInstanceOf(IllegalArgumentException.class);
    }
}