package chess.domain.strategy.direction;

import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LeftDownStrategyTest {
    @DisplayName("좌측 하단 이동 시 경로 구하기")
    @Test
    void findPathTest() {
        DirectionStrategy directionStrategy = new LeftDownStrategy();
        Position source = Position.of("g7");
        Position target = Position.of("b2");
        List<Position> path = directionStrategy.findPath(source, target);

        Assertions.assertThat(path).containsExactly(
                Position.of("c3"),
                Position.of("d4"),
                Position.of("e5"),
                Position.of("f6")
        );
    }
}
