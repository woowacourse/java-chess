package chess;

import chess.domain.Direction;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @ParameterizedTest
    @CsvSource(value ={"a1,b2,UP_RIGHT","a1,a2,UP",
    "a2,a1,DOWN","a1,b1,RIGHT"})
    @DisplayName("출발지와 목적지를 넘겨주면 방향을 가져온다.")
    void Direction_Find_direction_with_positions(String source, String target, Direction inputDirection) {
        Direction direction = Direction.findDirection(Position.of(source), Position.of(target));

        assertThat(direction).isEqualTo(inputDirection);
    }

}
