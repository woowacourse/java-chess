package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {
    @DisplayName("포지 생성")
    @Test
    void construct() {
        Position position = new Position("a", "1");
        assertThat(position).isNotNull();
    }
}
