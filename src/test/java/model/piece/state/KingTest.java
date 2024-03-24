package model.piece.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.direction.Direction;
import model.position.Position;
import model.piece.Color;
import model.position.Route;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class KingTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("King의 현재 위치에서 이동할 수 있는 모든 경로(Route)를 반환한다.")
    void possiblePositions_ReturnsPossiblePositions_WhenCurrentPositionIsGiven(Color color) {
        Role king = new King(color);
        Position initialPosition = Position.of(4, 4);
        Set<Route> routes = king.findPossibleAllRoute(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                // South-West
                new Route(Direction.SW, List.of(Position.of(3, 3))),
                // West
                new Route(Direction.W, List.of(Position.of(3, 4))),
                // North-West
                new Route(Direction.NW, List.of(Position.of(3, 5))),
                // South
                new Route(Direction.S, List.of(Position.of(4, 3))),
                // North
                new Route(Direction.N, List.of(Position.of(4, 5))),
                // South-East
                new Route(Direction.SE, List.of(Position.of(5, 3))),
                // East
                new Route(Direction.E, List.of(Position.of(5, 4))),
                // North-East
                new Route(Direction.NE, List.of(Position.of(5, 5)))
        );
        assertThat(routes).containsExactlyInAnyOrderElementsOf(expectedRoutes);
    }
}
