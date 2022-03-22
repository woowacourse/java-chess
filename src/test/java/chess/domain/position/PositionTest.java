package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    @DisplayName("열과 행을 갖는 Position을 생성")
    void position_createWithColumnAndRow() {
        final var positon = Position.of("a5");
        assertThat(positon).isInstanceOf(Position.class);
    }

}
