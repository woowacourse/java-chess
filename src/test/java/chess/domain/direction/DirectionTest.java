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
    @DisplayName("이동 경로 반환")
    void route(char column, char row, int expected) {
        Position position = Position.of('d', '4');
        Position movePosition = Position.of(column, row);
        List<Position> directions = Direction.UP.route(position, movePosition);

        assertThat(directions).hasSize(expected);
    }
}
