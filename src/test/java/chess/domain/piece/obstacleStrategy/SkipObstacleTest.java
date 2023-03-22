package chess.domain.piece.obstacleStrategy;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class SkipObstacleTest {

    @Test
    @DisplayName("장애물을 체크할 필요가 없으므로 빈 위치 목록을 반환한다.")
    void skipObstacleTest() {
        ObstacleStrategy obstacleStrategy = new SkipObstacle();
        List<Position> positions = obstacleStrategy.obstacleCheckingPositions(new Position(1, 0), new Position(2, 2));

        assertThat(positions.size()).isEqualTo(0);
    }

}
