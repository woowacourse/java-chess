package chess.domains.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DirectionTest {
    @DisplayName("입력한 이동 방향에 맞는 Direction 찾는 기능 테스트")
    @ParameterizedTest
    @CsvSource(value = {"0,1,NORTH", "0,-1,SOUTH", "1,0,EAST", "-1,0,WEST",
            "1,1,NORTHEAST", "1,-1,SOUTHEAST", "-1,-1,SOUTHWEST", "-1,1,NORTHWEST"})
    void findDirection(int xGap, int yGap, Direction expected) {
        Direction actual = Direction.findDirection(xGap, yGap);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("예외 테스트: 입력한 이동 방향에 맞는 Direction이 없는 경우 예외 발생")
    @ParameterizedTest
    @CsvSource(value = {"3,1", "0,0"})
    void findDirection_1(int xGap, int yGap) {
        assertThatThrownBy(() -> Direction.findDirection(xGap, yGap))
                .isInstanceOf(NullPointerException.class);
    }
}