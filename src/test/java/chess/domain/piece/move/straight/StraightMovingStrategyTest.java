package chess.domain.piece.move.straight;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.EmptyPoints;
import chess.domain.board.Point;
import chess.domain.board.Route;
import chess.domain.piece.EmptyPointsFixtures;
import chess.domain.piece.move.MovingStrategy;

class StraightMovingStrategyTest {

    @Test
    @DisplayName("이동가능 여부를 판단한다.")
    public void move() {
        // given
        Route route = Route.of(List.of("a1", "a2"));
        EmptyPoints emptyPoints = EmptyPointsFixtures.ALL;
        MovingStrategy strategy = new StraightMovingStrategy(
            List.of(StraightDirection.NORTH), InfiniteStepDistance.init()
        );
        // when
        boolean isMovable = strategy.move(route, emptyPoints);
        // then
        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("장애물이 있으면 이동할 수 없다.")
    public void falseWithObstacle() {
        // given
        Route route = Route.of(List.of("a1", "a3"));
        EmptyPoints emptyPoints = EmptyPointsFixtures.except(Point.of("a2"));

        MovingStrategy strategy = new StraightMovingStrategy(
            List.of(StraightDirection.NORTH), InfiniteStepDistance.init()
        );
        // when
        boolean isMovable = strategy.move(route, emptyPoints);
        // then
        assertThat(isMovable).isFalse();
    }

    @Test
    @DisplayName("갈 수 있는 방향이 아니면 이동할 수 없다.")
    public void falseWithWrongDirection() {
        // given
        Route route = Route.of(List.of("a1", "a3"));
        EmptyPoints emptyPoints = EmptyPointsFixtures.except(Point.of("a2"));

        MovingStrategy strategy = new StraightMovingStrategy(
            List.of(StraightDirection.EAST), InfiniteStepDistance.init()
        );
        // when
        boolean isMovable = strategy.move(route, emptyPoints);
        // then
        assertThat(isMovable).isFalse();
    }

    @Test
    @DisplayName("이동할 수 있는 거리가 짧으면 이동할 수 없다.")
    public void falseWithShortDirection() {
        Route route = Route.of(List.of("a1", "a3"));
        EmptyPoints emptyPoints = EmptyPointsFixtures.except(Point.of("a2"));

        MovingStrategy strategy = new StraightMovingStrategy(
            List.of(StraightDirection.EAST), OneStepDistance.init()
        );
        // when
        boolean isMovable = strategy.move(route, emptyPoints);
        // then
        assertThat(isMovable).isFalse();
    }
}