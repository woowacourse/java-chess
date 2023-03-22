package chess.domain.piece.obstacleStrategy;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


class BlockedByObstacleTest {

    @Test
    @DisplayName("이동 경로의 장애물 확인이 필요한 위치들을 반환한다.")
    void SkipObstacleTest() {
        ObstacleStrategy obstacleStrategy = new BlockedByObstacle();
        List<Position> positions = obstacleStrategy.obstacleCheckingPositions(new Position(1, 0), new Position(5, 0));

        assertThat(positions).containsExactly(new Position(2, 0), new Position(3, 0), new Position(4, 0));
    }

}
