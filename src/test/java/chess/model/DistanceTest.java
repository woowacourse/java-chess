package chess.model;

import chess.model.position.Direction;
import chess.model.position.Distance;
import chess.model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DistanceTest {
    @ParameterizedTest
    @DisplayName("방향에 따른 올바른 거리를 구한다.")
    @CsvSource(value = {"e4:1", "b5:2", "d4:1", "d1:2", "a3:3", "e3:1", "e5:1", "b2:1", "f7:2"}, delimiter = ':')
    void diagonal1(String targetPosition, int value) {
        Position source = Position.from("d3");
        Position target = Position.from(targetPosition);

        Direction direction = Direction.of(source, target);
        Distance distance = Distance.of(source, target, direction);

        assertThat(distance).isEqualTo(new Distance(value));
    }
}
