package chess.model.domain.position;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.exception.ObstacleExistException;
import java.util.Set;
import org.junit.jupiter.api.Test;

class PathTest {
    @Test
    void test_validateObstacle() {
        Position position = new Position(1, 1);
        Path path = new Path(position);

        assertThatThrownBy(() -> path.validateObstacle(Set.of(position)))
                .isInstanceOf(ObstacleExistException.class);
    }
}
