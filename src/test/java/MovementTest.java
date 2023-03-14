import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MovementTest {

    @DisplayName("target position으로 폰이 이동 가능 여부를 반환한다.")
    @Test
    void shouldCheckIsMovableToTargetPositionWhenPieceIsPawn() {
        Movement movement = new Movement(0, 1);
        assertThat(movement.canMovedByPawn()).isTrue();
    }

}