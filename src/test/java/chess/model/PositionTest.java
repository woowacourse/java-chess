package chess.model;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @DisplayName("x와 y좌표에 해당하는 Position 객체를 생성한다")
    @Test
    void createValidPosition() {
        assertThatCode(() -> new Position(3, 6))
                .doesNotThrowAnyException();
    }

}
