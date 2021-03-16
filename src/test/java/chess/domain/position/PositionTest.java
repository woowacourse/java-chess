package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    @DisplayName("위치 값으로 위치 생성")
    void create() {
        final Position a1 = new Position("a", "1");
        assertThat(a1).isEqualTo(new Position("a", "1"));
    }
}