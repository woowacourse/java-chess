package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    @DisplayName("Postion이 정상적으로 생성되어야 한다.")
    void create_Success() {
        // given
        Position position = Position.of(4, 6);

        // expect
        assertThat(position.getX())
                .isEqualTo(4);
        assertThat(position.getY())
                .isEqualTo(6);
    }
}
