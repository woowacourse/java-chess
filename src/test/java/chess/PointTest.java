package chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PointTest {
    @DisplayName("좌표 생성")
    @Test
    void create() {
        Assertions.assertDoesNotThrow(
                () -> new Point('a', 1)
        );
    }
}