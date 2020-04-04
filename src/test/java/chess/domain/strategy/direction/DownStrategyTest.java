package chess.domain.strategy.direction;

import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DownStrategyTest {
    @DisplayName("하단 이동 시 경로 구하기")
    @Test
    void findPathTest() {
        DirectionStrategy directionStrategy = new DownStrategy();
        Position source = Position.of("a7");
        Position target = Position.of("a2");
        List<Position> path = directionStrategy.findPath(source, target);

        Assertions.assertThat(path).containsExactly(
                Position.of("a3"),
                Position.of("a4"),
                Position.of("a5"),
                Position.of("a6")
        );
    }
}
