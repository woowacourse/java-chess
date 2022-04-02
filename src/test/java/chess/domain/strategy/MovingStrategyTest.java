package chess.domain.strategy;

import chess.domain.ChessBoardPosition;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MovingStrategyTest {

    @Test
    @DisplayName("연속적 움직임 전략을 가질 때 두 위치 사이 경로를 구한다")
    void makePathTest() {
        MovingStrategy movingStrategy = new ContinuousMovingStrategy(
                List.of(ChessBoardPosition.ofDirection(-1, 1),
                        ChessBoardPosition.ofDirection(1, -1),
                        ChessBoardPosition.ofDirection(1, 1),
                        ChessBoardPosition.ofDirection(-1, -1)
                ));
        ChessBoardPosition sourcePosition = ChessBoardPosition.of(1, 1);
        ChessBoardPosition targetPosition = ChessBoardPosition.of(4, 4);
        List<ChessBoardPosition> path = movingStrategy.makePath(sourcePosition, targetPosition);
        assertThat(path.size() == 2
                && path.get(0).equals(ChessBoardPosition.of(2, 2))
                && path.get(1).equals(ChessBoardPosition.of(3, 3))).isTrue();
    }

    @Test
    @DisplayName("갈 수 없는 목적지이면 예외를 발생한다.")
    void makePathTest2() {
        MovingStrategy movingStrategy = new ContinuousMovingStrategy(
                List.of(ChessBoardPosition.ofDirection(-1, 1),
                        ChessBoardPosition.ofDirection(1, -1),
                        ChessBoardPosition.ofDirection(1, 1),
                        ChessBoardPosition.ofDirection(-1, -1)
                ));
        ChessBoardPosition sourcePosition = ChessBoardPosition.of(1, 1);
        ChessBoardPosition targetPosition = ChessBoardPosition.of(4, 3);
        assertThatThrownBy(() -> movingStrategy.makePath(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
