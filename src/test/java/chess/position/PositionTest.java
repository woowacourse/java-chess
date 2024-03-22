package chess.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    @DisplayName("동등성을 올바르게 판단한다.")
    void equalsTest() {
        // given
        Position position = Position.of(File.A, Rank.ONE);
        // when, then
        assertThat(position).isEqualTo(Position.of(File.A, Rank.ONE));
    }
}
