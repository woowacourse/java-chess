package model.piece.state;

import model.direction.Direction;
import model.piece.Color;
import model.position.Position;
import model.position.Route;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("Bishop의 현재 위치에서 이동할 수 있는 모든 경로(Route)를 반환한다.")
    void possiblePositions_ReturnPossiblePositions_WhenCurrentPositionIsGiven(Color color) {
        Role bishop = new Bishop(color);
        Position initialPosition = Position.of(4, 4);
        Set<Route> routes = bishop.findPossibleAllRoute(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                // North-West
                new Route(Direction.NW, List.of(Position.of(3, 5), Position.of(2, 6), Position.of(1, 7))),
                // South-West
                new Route(Direction.SW, List.of(Position.of(3, 3), Position.of(2, 2), Position.of(1, 1))),
                // North-East
                new Route(Direction.NE, List.of(Position.of(5, 5), Position.of(6, 6), Position.of(7, 7), Position.of(8, 8))),
                // South-East
                new Route(Direction.SE, List.of(Position.of(5, 3), Position.of(6, 2), Position.of(7, 1))));

        assertThat(routes).containsExactlyInAnyOrderElementsOf(expectedRoutes);
    }
}
