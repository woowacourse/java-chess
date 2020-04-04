package chess.domain.strategy.direction;

import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RightUpStrategyTest {
    @DisplayName("우측 상단 이동 시 경로 구하기")
    @Test
    void findPathTest() {
        DirectionStrategy directionStrategy = new RightUpStrategy();
        Position source = Position.of("b2");
        Position target = Position.of("g7");
        List<Position> path = directionStrategy.findPath(source, target);

        Assertions.assertThat(path).containsExactly(
                Position.of("c3"),
                Position.of("d4"),
                Position.of("e5"),
                Position.of("f6")
        );
    }
}
