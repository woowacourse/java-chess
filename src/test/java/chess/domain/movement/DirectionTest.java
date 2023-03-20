package chess.domain.movement;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DirectionTest {

    @ParameterizedTest(name = "이동한 칸의 방향에 따라 Direction를 반환한다.")
    @CsvSource({
            "1,1,DIAGONAL",
            "2,1,L_SHAPE",
            "2,0,STRAIGHT",
            "4,8,OTHER"})
    void checkDirection(int fileInterval, int rankInterval, Direction direction) {
        assertThat(Direction.of(fileInterval, rankInterval)).isEqualTo(direction);
    }
}
