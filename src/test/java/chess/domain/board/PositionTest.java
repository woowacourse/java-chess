package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {
    @DisplayName("포지션 Equal Test")
    @Test
    void PositionEqualTest() {
        Position position1 = Position.of(Row.FOUR, Column.B);
        Position position2 = Position.of(Row.FOUR, Column.B);
        assertThat(position1).isEqualTo(position2);
    }
}
