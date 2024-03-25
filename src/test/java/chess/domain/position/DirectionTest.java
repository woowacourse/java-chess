package chess.domain.position;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @ParameterizedTest
    @CsvSource(value = {
            "a1, a2, UP",
            "a2,a1,DOWN",
            "b1, a1, LEFT",
            "a1, b1, RIGHT",
            "b1, a2, UP_LEFT",
            "a1,b2,UP_RIGHT",
            "b2, a1, DOWN_LEFT",
            "a2, b1, DOWN_RIGHT"})
    @DisplayName("출발지와 목적지를 넘겨주면 방향을 가져온다.")
    void Direction_Find_direction_with_positions(Position source,
                                                 Position target,
                                                 Direction inputDirection) {
        Direction direction = Direction.findDirection(source, target);

        assertThat(direction).isEqualTo(inputDirection);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "a1, a2, true",
            "b3, b1, true",
            "a3, b2, false"})
    void Direction_Check_up_and_down_with_position(Position source,
                                                   Position target,
                                                   boolean isUpDown) {
        var result = Direction.isUpDown(source, target);

        assertThat(result).isEqualTo(isUpDown);
    }
}
