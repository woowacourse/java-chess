package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DirectionTest {
    @DisplayName("좌표에 맞는 방향 목록에 있는 방향을 생성하기")
    @ParameterizedTest
    @CsvSource(value = {"1, 1, NORTHEAST"})
    void of1(int x, int y, Direction direction) {
        assertThat(Direction.of(x, y)).isEqualTo(direction);
    }

    @DisplayName("좌표에 맞는 방향이 방향 목록에 없는 경우 에러 발생")
    @ParameterizedTest
    @CsvSource(value = {"0, 0", "1, 5"})
    void of2(int x, int y) {
        assertThatThrownBy(() -> Direction.of(x, y)).isInstanceOf(IllegalArgumentException.class);
    }
}