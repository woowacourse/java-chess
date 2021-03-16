package chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PointTest {
    @DisplayName("좌표 생성")
    @Test
    void create() {
        assertDoesNotThrow(
                () -> new Point('a', 8)
        );
        assertDoesNotThrow(
                () -> new Point('h', 1)
        );
    }

    @DisplayName("범위 밖 좌표 생성 시 예외 처리")
    @Test
    void checkIndexOutOfRange() {
        assertThatThrownBy(()-> new Point('i', 0)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(()-> new Point('i', 8)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(()-> new Point('h', 0)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(()-> new Point('h', 9)).isInstanceOf(IllegalArgumentException.class);
    }
}