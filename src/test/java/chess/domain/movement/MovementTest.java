package chess.domain.movement;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MovementTest {

    @ParameterizedTest(name = "이동한 칸에 따라 movement를 반환한다.")
    @CsvSource({
            "1,1,DISCONTINUOUS_DIAGONAL",
            "2,1,CONTINUOUS_L_SHAPE",
            "2,0,CONTINUOUS_STRAIGHT"})
    void move(int fileInterval, int rankInterval, Movement movement) {
        assertThat(Movement.of(fileInterval, rankInterval)).isEqualTo(movement);
    }
}
