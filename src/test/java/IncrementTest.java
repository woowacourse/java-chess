import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IncrementTest {

    @DisplayName("target position으로 폰이 이동 가능 여부를 반환한다.")
    @Test
    void shouldCheckIsMovableToTargetPositionWhenPieceIsPawn() {
        Increment increment = new Increment(0, 1);
        assertThat(increment.canMovedByPawn()).isTrue();
    }

}