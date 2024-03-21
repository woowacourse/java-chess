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

class BishopTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("Bishop의 현재 위치에서 이동할 수 있는 Position을 반환한다.")
    void possiblePositions_ReturnPossiblePositions_WhenCurrentPositionIsGiven(Color color) {
        Role bishop = new Bishop(color);
        Position initialPosition = Position.of(4, 4);
        Set<Route> routes = bishop.possibleRoutes(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                // North-West
                new Route(List.of(Position.of(3, 5), Position.of(2, 6), Position.of(1, 7))),
                // South-West
                new Route(List.of(Position.of(3, 3), Position.of(2, 2), Position.of(1, 1))),
                // North-East
                new Route(List.of(Position.of(5, 5), Position.of(6, 6), Position.of(7, 7), Position.of(8, 8))),
                // South-East
                new Route(List.of(Position.of(5, 3), Position.of(6, 2), Position.of(7, 1))));

        assertEquals(expectedRoutes, routes);
    }
}
