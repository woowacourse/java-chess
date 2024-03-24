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

class KnightTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("Knight의 현재 위치에서 이동할 수 있는 모든 경로(Route)를 반환한다.")
    void possiblePositions_ReturnsPossiblePositions_WhenCurrentPositionIsGiven(Color color) {
        Role knight = new Knight(color);
        Position initialPosition = Position.of(4, 4);
        Set<Route> routes = knight.findPossibleAllRoute(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                // EE_
                new Route(Direction.EEN, List.of(Position.of(6, 5))), new Route(Direction.EES, List.of(Position.of(6, 3))),
                // WW_
                new Route(Direction.WWN, List.of(Position.of(2, 5))), new Route(Direction.WWS, List.of(Position.of(2, 3))),
                // NN_
                new Route(Direction.NNE, List.of(Position.of(5, 6))), new Route(Direction.NNW, List.of(Position.of(3, 6))),
                // SS_
                new Route(Direction.SSE, List.of(Position.of(5, 2))), new Route(Direction.SSW, List.of(Position.of(3, 2)))
        );

        assertThat(routes).containsExactlyInAnyOrderElementsOf(expectedRoutes);
    }
}
