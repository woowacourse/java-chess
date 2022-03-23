package chess.domain.direction;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DirectionTest {

    @ParameterizedTest
    @CsvSource(value = {"d,4,0", "d,7,3", "d,8,4", "g,7,0", "d,1,0", "h,4,0"})
    @DisplayName("멀티 이동 시 이동 방향이 맞는지 확인")
    void isDirectionByNotSingleMovable(char column, char row, int expected) {
        Position position = new Position('d', '4');
        Position movePosition = new Position(column, row);

        List<Direction> directions = Direction.UP.route(position, movePosition, false);
        assertThat(directions).hasSize(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"d,4,0", "d,5,1", "e,5,0", "d,6,0", "e,6,0"})
    @DisplayName("단일 이동 시 이동 방향이 맞는지 확인")
    void isDirectionBySingleMovable(char column, char row, int expected) {
        Position position = new Position('d', '4');
        Position movePosition = new Position(column, row);

        List<Direction> directions = Direction.UP.route(position, movePosition, true);
        assertThat(directions).hasSize(expected);
    }
}
