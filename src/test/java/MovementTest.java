import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class MovementTest {

    @DisplayName("file, rank움직임이 0,0인지 확인한다.")
    @Test
    void shouldReturnTrueWhenMovementIsStop() {
        Movement movement = new Movement(0, 0);
        assertThat(movement.isStop()).isTrue();
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

    @DisplayName("2칸 이내의 움직임이면 true, 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"2,0,true", "0,1,true", "2,2,true", "-2,2,true", "-3,1,false"})
    void shouldReturnTrueWhenMovementDirectionIsUnderTwoStepsOrFalse(int fileIncrement, int rankIncrement, boolean result) {
        Movement movement = new Movement(fileIncrement, rankIncrement);
        assertThat(movement.isUnderTwoSteps()).isEqualTo(result);
    }

    @DisplayName("수직으로 상, 하, 좌, 우 방향의 움직임이면 true, 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"2,0,true", "0,1,true", "-2,0,true", "0,-3,true", "1,1,false", "-1,3,false"})
    void shouldReturnTrueWhenMovementDirectionIsPerpendicularOrFalse(int fileIncrement, int rankIncrement, boolean result) {
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


}