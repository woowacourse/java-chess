package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @DisplayName("칸의 위치를 생성한다")
    @Test
    void generateSquare() {
        Position position = new Position(1, 1);
        Assertions.assertThat(position).isEqualTo(new Position(1, 1));
    }
}
