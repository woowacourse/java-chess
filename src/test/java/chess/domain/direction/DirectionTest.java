package chess.domain.direction;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DirectionTest {

    @ParameterizedTest
    @CsvSource(value = {"d,7,true", "d,8,true", "g,7,false", "d,1,false", "h,4,false"})
    @DisplayName("이동 방향이 맞는지 확인")
    void isDirection(char column, char row, boolean expected) {
        Position position = new Position('d', '4');
        Position movePosition = new Position(column, row);

        boolean actual = Direction.UP.isDirection(position, movePosition);
        assertThat(actual).isEqualTo(expected);
    }
}
