package chess.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    @DisplayName("x, y 좌표로 Position 객체가 생성된다.")
    void createPositionByCoordinateTest() {
        Position position = Position.of("a1");

        Assertions.assertThat(position).isNotNull();
    }
}
