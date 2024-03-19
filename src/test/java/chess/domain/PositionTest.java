package chess.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    @DisplayName("위치는 가로, 세로 좌표값을 가진다.")
    void createPosition() {
        assertThatCode(() -> new Position(1, 1))
                .doesNotThrowAnyException();
    }
}
