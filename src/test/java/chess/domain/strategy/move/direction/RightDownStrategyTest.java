package chess.domain.strategy.move.direction;

import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RightDownStrategyTest {
    @DisplayName("우측 하단 이동 시 경로 구하기")
    @Test
    void findPathTest() {
        DirectionStrategy directionStrategy = new RightDownStrategy();
        Position source = Position.of("b7");
        Position target = Position.of("g2");
        List<Position> path = directionStrategy.findPath(source, target);

        Assertions.assertThat(path).containsExactly(
                Position.of("c6"),
                Position.of("d5"),
                Position.of("e4"),
                Position.of("f3")
        );
    }
}
