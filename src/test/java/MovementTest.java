import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MovementTest {

    @DisplayName("target position으로 폰이 이동 가능 여부를 반환한다.")
    @Test
    void shouldCheckIsMovableToTargetPositionWhenPieceIsPawn() {
        Movement movement = new Movement(0, 1);
        assertThat(movement.canMovedByPawn()).isTrue();
    }

    @DisplayName("file, rank움직임이 0,0이 되면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenMovingIsAllZero() {
        assertThatThrownBy(() -> new Movement(0, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 움직임입니다.");
    }
}