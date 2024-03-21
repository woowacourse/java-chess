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

class KnightTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("Knight의 현재 위치에서 이동할 수 있는 Position을 반환한다.")
    void possiblePositions_ReturnsPossiblePositions_WhenCurrentPositionIsGiven(Color color) {
        Role knight = new Knight(color);
        Position initialPosition = Position.of(4, 4);
        Set<Route> routes = knight.possibleRoutes(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                // NN_
                new Route(List.of(Position.of(6, 5))), new Route(List.of(Position.of(6, 3))),
                // SS_
                new Route(List.of(Position.of(2, 5))), new Route(List.of(Position.of(2, 3))),
                // EE_
                new Route(List.of(Position.of(5, 6))), new Route(List.of(Position.of(3, 6))),
                // WW_
                new Route(List.of(Position.of(5, 2))), new Route(List.of(Position.of(3, 2)))
        );

        assertEquals(expectedRoutes, routes);
    }
}
