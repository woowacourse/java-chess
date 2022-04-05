package refactorChess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DirectionTest {

    @ParameterizedTest
    @CsvSource(value = {"0, 1, NORTH", "0, -1, SOUTH", "1, 0, EAST", "-1, 0, WEST"})
    @DisplayName("기물이 움직이는 방향을 생성할 수 있다.")
    void createValidDirection(int column, int row, Direction direction) {
        assertThat(Direction.of(column, row)).isEqualTo(direction);
    }

    @ParameterizedTest
    @CsvSource(value = {"-2, -2", "0, 0"})
    @DisplayName("기물이 움직이는 방향에 대하여 값이 존재하지 않은 경우 예외가 발생한다.")
    void createInvalidDirection(int column, int row) {
        assertThatThrownBy(() -> Direction.of(column, row))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 방향입니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"0, 1, NORTH", "0, -1, SOUTH", "1, 0, EAST", "-1, 0, WEST"})
    @DisplayName("기물이 움직이는 방향이 직선인 경우 직선에 해당하는 방향 값을 생성할 수 있다.")
    void createValidLinearDirection(int column, int row, Direction direction) {
        assertThat(Direction.ofLinear(column, row)).isEqualTo(direction);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 1, NORTH_EAST", "-1, -1, SOUTH_WEST", "-1, 1, NORTH_WEST", "1, -1, SOUTH_EAST"})
    @DisplayName("기물이 움직이는 방향이 대각선인 경우 대각선에 해당하는 방향 값을 생성할 수 있다.")
    void createValidDiagonalDirection(int column, int row, Direction direction) {
        assertThat(Direction.ofDiagonal(column, row)).isEqualTo(direction);
    }
}
