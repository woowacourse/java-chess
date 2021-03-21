package domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @DisplayName("Position이 정상적으로 생성되는 경우")
    @Test
    void position_generate() {
        Position position = new Position(0, 0);
        assertThat(position.getRow()).isEqualTo(0);
        assertThat(position.getColumn()).isEqualTo(0);
    }
}