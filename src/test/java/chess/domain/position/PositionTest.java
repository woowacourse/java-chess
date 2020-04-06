package chess.domain.position;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {
    @DisplayName("입력한 값으로 position 변경하는 기능")
    @Test
    void moveTest() {
        Position position = Position.of("b2");

        Assertions.assertThat(position).isEqualTo(Position.of("b2"));
    }
}
