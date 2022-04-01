package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DirectionTest {

    @Test
    @DisplayName("columnValue, rowValue를 이용해 Direction을 구한다.")
    void getDirectionByValues() {
        final Direction directionByValues = Direction.getDirectionByValues(1, 0);

        assertThat(directionByValues).isEqualTo(Direction.EAST);
    }

    @Test
    @DisplayName("columnValue, rowValue가 범위를 벗어날 경우 예외가 발생한다.")
    void getDirectionByValuesThrowException() {
        assertThatThrownBy(() ->
                Direction.getDirectionByValues(-5, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("일치하는 Direction이 없습니다.");
    }

    @Test
    @DisplayName("현재 방향에서의 대각선 방향들을 구한다.")
    void getDiagonal() {
        final List<Direction> diagonal = Direction.EAST.getDiagonal();

        assertThat(diagonal).contains(Direction.NORTH_EAST, Direction.SOUTH_EAST);
    }

    @Test
    @DisplayName("현재 방향이 대각선을 구할 수 없는 경우 예외가 발생한다.")
    void getDiagonalThrowException() {
        assertThatThrownBy(() ->
                Direction.NORTH_EAST.getDiagonal())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("해당 디렉션의 대각선을 구할 수 없습니다");
    }
}
