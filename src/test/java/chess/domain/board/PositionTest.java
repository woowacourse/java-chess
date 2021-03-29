package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @DisplayName("Position 캐싱 확인")
    @Test
    void create() {
        Position expectedPosition = Position.of(Horizontal.C, Vertical.FIVE);
        assertThat(expectedPosition).isEqualTo(Position.of(Horizontal.C, Vertical.FIVE));
    }
}