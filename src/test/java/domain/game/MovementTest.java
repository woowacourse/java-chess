package domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Movement는")
class MovementTest {

    @DisplayName("한 칸도 움직이지 않으면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenNonMoving() {
        assertThatThrownBy(() -> new Movement(0, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최소 한 칸은 움직여야 합니다.");
    }

    @DisplayName("위 방향의 움직임이면 true, 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"0,1,true", "0,-1,false", "1,1,true", "-1,1,true"})
    void shouldReturnTrueWhenMovementDirectionIsUpwardOrFalse(int fileIncrement, int rankIncrement, boolean result) {
        Movement movement = new Movement(fileIncrement, rankIncrement);
        assertThat(movement.isUpward()).isEqualTo(result);
    }

    @DisplayName("아래 방향의 움직임이면 true, 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"0,-1,true", "0,1,false", "1,-1,true", "-1,-1,true"})
    void shouldReturnTrueWhenMovementDirectionIsDownwardOrFalse(int fileIncrement, int rankIncrement, boolean result) {
        Movement movement = new Movement(fileIncrement, rankIncrement);
        assertThat(movement.isDownward()).isEqualTo(result);
    }

    @DisplayName("오른쪽 방향의 움직임이면 true, 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"1,0,true", "1,1,true", "-1,0,false", "2,-1,true"})
    void shouldReturnTrueWhenMovementDirectionIsRightOrFalse(int fileIncrement, int rankIncrement, boolean result) {
        Movement movement = new Movement(fileIncrement, rankIncrement);
        assertThat(movement.isRight()).isEqualTo(result);
    }

    @DisplayName("왼쪽 방향의 움직임이면 true, 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"-1,0,true", "-1,1,true", "1,0,false", "-2,-1,true"})
    void shouldReturnTrueWhenMovementDirectionIsLeftOrFalse(int fileIncrement, int rankIncrement, boolean result) {
        Movement movement = new Movement(fileIncrement, rankIncrement);
        assertThat(movement.isLeft()).isEqualTo(result);
    }

    @DisplayName("2칸 이내의 움직임이면 true, 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"2,0,true", "0,1,true", "2,2,true", "-2,2,true", "-3,1,false"})
    void shouldReturnTrueWhenMovementDirectionIsUnderTwoStepsOrFalse(
            int fileIncrement,
            int rankIncrement,
            boolean result) {
        Movement movement = new Movement(fileIncrement, rankIncrement);
        assertThat(movement.isUnderTwoSteps()).isEqualTo(result);
    }

    @DisplayName("수직으로 상, 하, 좌, 우 방향의 움직임이면 true, 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"2,0,true", "0,1,true", "-2,0,true", "0,-3,true", "1,1,false", "-1,3,false"})
    void shouldReturnTrueWhenMovementDirectionIsPerpendicularOrFalse(
            int fileIncrement,
            int rankIncrement,
            boolean result) {
        Movement movement = new Movement(fileIncrement, rankIncrement);
        assertThat(movement.isPerpendicular()).isEqualTo(result);
    }

    @DisplayName("대각선 방향의 움직임이면 true, 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"1,1,true", "-1,1,true", "-1,-1,true", "1,-1,true", "1,0,false", "-1,3,false"})
    void shouldReturnTrueWhenMovementDirectionIsDiagonalOrFalse(int fileIncrement, int rankIncrement, boolean result) {
        Movement movement = new Movement(fileIncrement, rankIncrement);
        assertThat(movement.isDiagonal()).isEqualTo(result);
    }

    @DisplayName("'수직 2칸 + 수평 1칸' 또는 '수직 1칸 + 수평 1칸'의 움직임일 경우 true, 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"1,2,true", "2,1,true", "-1,-2,true", "-2,1,true", "1,0,false", "-1,3,false", "2,2,false"})
    void shouldReturnTrueWhenMovementIsCorrectForKnightOrFalse(int fileIncrement, int rankIncrement, boolean result) {
        Movement movement = new Movement(fileIncrement, rankIncrement);
        assertThat(movement.isThreeStepAndNotPerpendicular()).isEqualTo(result);
    }
}
