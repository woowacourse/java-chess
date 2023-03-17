package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Path;
import chess.domain.position.Position;
import java.util.Set;
import org.junit.jupiter.api.Test;

class PathTest {
    @Test
    void test_validateObstacle() {
        Position position = new Position(1, 1);
        Path path = new Path(position);

        assertThatThrownBy(() -> path.validateObstacle(Set.of(position)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("중간에 다른 기물이 존재합니다.");
    }
}
