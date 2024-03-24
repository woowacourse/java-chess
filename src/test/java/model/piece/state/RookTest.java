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

class RookTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("Rook의 현재 위치에서 이동할 수 있는 모든 경로(Route)를 반환한다.")
    void possiblePositions_ReturnPossiblePositions_WhenCurrentPositionIsGiven(Color color) {
        Role rook = new Rook(color);
        Position initialPosition = Position.of(4, 4);
        Set<Route> routes = rook.findPossibleAllRoute(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                // North
                new Route(Direction.N, List.of(Position.of(4, 5), Position.of(4, 6), Position.of(4, 7), Position.of(4, 8))),
                // South
                new Route(Direction.S, List.of(Position.of(4, 3), Position.of(4, 2), Position.of(4, 1))),
                // East
                new Route(Direction.E, List.of(Position.of(5, 4), Position.of(6, 4), Position.of(7, 4), Position.of(8, 4))),
                // West
                new Route(Direction.W, List.of(Position.of(3, 4), Position.of(2, 4), Position.of(1, 4)))
        );

        assertThat(routes).containsExactlyInAnyOrderElementsOf(expectedRoutes);
    }
}
