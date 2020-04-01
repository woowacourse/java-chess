package chess.domain.strategy.direction;

import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RightStrategyTest {
    @DisplayName("우측 이동 시 경로 구하기")
    @Test
    void findPathTest() {
        DirectionStrategy directionStrategy = new RightStrategy();
        Position source = Position.of("b2");
        Position target = Position.of("g2");
        List<Position> path = directionStrategy.findPath(source, target);

        Assertions.assertThat(path).containsExactly(
                Position.of("c2"),
                Position.of("d2"),
                Position.of("e2"),
                Position.of("f2")
        );
    }
}
