package chess.domain.strategy.direction;

import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UpStrategyTest {
    @DisplayName("상단 이동 시 경로 구하기")
    @Test
    void findPathTest() {
        DirectionStrategy directionStrategy = new UpStrategy();
        Position source = Position.of("b2");
        Position target = Position.of("b7");
        List<Position> path = directionStrategy.findPath(source, target);

        Assertions.assertThat(path).containsExactly(
                Position.of("b3"),
                Position.of("b4"),
                Position.of("b5"),
                Position.of("b6")
        );
    }
}
