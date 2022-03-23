package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PositionTest {
    @Test
    @DisplayName("a1을 입력하면 0, 0을 가져온다.")
    void getPosition() {
        Position position = Position.from("a1");
        assertThat(position.getColumn()).isEqualTo(0);
        assertThat(position.getRow()).isEqualTo(0);
    }
}
