package chess.domain.movement;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MovementTest {

    @ParameterizedTest(name = "이동한 칸에 따라 Movement 반환한다.")
    @CsvSource({
            "1,1,DISCONTINUOUS_DIAGONAL",
            "2,1,CONTINUOUS_L_SHAPE",
            "2,0,CONTINUOUS_STRAIGHT"})
    void checkMovement(int fileInterval, int rankInterval, Movement movement) {
        assertThat(Movement.of(fileInterval, rankInterval)).isEqualTo(movement);
    }

    @ParameterizedTest(name = "이동한 칸의 움직임이 Movement에 없다면, 예외를 발생한다.")
    @CsvSource({"1,4", "0,0", "-3, -9"})
    void validateMovement(int fileInterval, int rankInterval) {
        assertThatThrownBy(() -> Movement.of(fileInterval, rankInterval))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("체스 규칙에서 존재하지 않는 움직임입니다.");
    }
}
