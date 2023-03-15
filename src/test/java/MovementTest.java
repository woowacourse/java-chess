import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @DisplayName("위 방향의 움직임이면 true, 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"0,1,true", "0,-1,false"})
    void shouldReturnTrueWhenMovementDirectionIsUpwardOrFalse(int fileIncrement, int rankIncrement, boolean result) {
        Movement movement = new Movement(fileIncrement, rankIncrement);
        Assertions.assertThat(movement.isUpward()).isEqualTo(result);
    }

    @DisplayName("아래 방향의 움직임이면 true, 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"0,-1,true", "0,1,false"})
    void shouldReturnTrueWhenMovementDirectionIsDownwardOrFalse(int fileIncrement, int rankIncrement, boolean result) {
        Movement movement = new Movement(fileIncrement, rankIncrement);
        Assertions.assertThat(movement.isDownward()).isEqualTo(result);
    }

    @DisplayName("2칸 이내의 움직임이면 true, 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"2,0,true", "0,1,true", "2,2,true", "-2,2,true", "-3,1,false"})
    void shouldReturnTrueWhenMovementDirectionIsUnderTwoStepsOrFalse(int fileIncrement, int rankIncrement, boolean result) {
        Movement movement = new Movement(fileIncrement, rankIncrement);
        Assertions.assertThat(movement.isUnderTwoSteps()).isEqualTo(result);
    }
}