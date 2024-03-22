package model.piece.state;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Set;
import model.piece.Color;
import model.position.Position;
import model.position.Route;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class KingTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("King의 현재 위치에서 이동할 수 있는 Position을 반환한다.")
    void possiblePositions_ReturnsPossiblePositions_WhenCurrentPositionIsGiven(Color color) {
        Role king = King.from(color);
        Position initialPosition = Position.of(4, 4);
        Set<Route> routes = king.possibleRoutes(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                // South-West
                new Route(List.of(Position.of(3, 3))),
                // West
                new Route(List.of(Position.of(3, 4))),
                // North-West
                new Route(List.of(Position.of(3, 5))),
                // South
                new Route(List.of(Position.of(4, 3))),
                // North
                new Route(List.of(Position.of(4, 5))),
                // North-East
                new Route(List.of(Position.of(5, 3))),
                // East
                new Route(List.of(Position.of(5, 4))),
                // South-East
                new Route(List.of(Position.of(5, 5)))
        );
        assertEquals(expectedRoutes, routes);
    }
}
