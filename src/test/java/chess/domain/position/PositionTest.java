package chess.domain.position;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {
    @DisplayName("포지션은 x축 대칭인 포지션을 반환할 수 있다")
    @Test
    void should_ReturnVerticalReversePosition() {
        Position testPosition = Position.of(0, 0);

        assertThat(testPosition.verticalReversePosition()).isEqualTo(Position.of(7, 0));
    }

}
