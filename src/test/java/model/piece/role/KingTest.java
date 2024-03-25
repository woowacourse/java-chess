package model.piece.role;

import model.direction.Direction;
import model.direction.Route;
import model.piece.Color;
import model.position.File;
import model.position.Position;
import model.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("King의 현재 위치에서 이동할 수 있는 모든 경로(Route)를 반환한다.")
    void possiblePositions_ReturnsPossiblePositions_WhenCurrentPositionIsGiven(Color color) {
        Role king = new King(color);
        Position initialPosition = Position.of(File.D, Rank.FOUR);
        Set<Route> routes = king.findPossibleAllRoute(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                // South-West
                new Route(Direction.SW, List.of(Position.of(File.C, Rank.THREE))),
                // West
                new Route(Direction.W, List.of(Position.of(File.C, Rank.FOUR))),
                // North-West
                new Route(Direction.NW, List.of(Position.of(File.C, Rank.FIVE))),
                // South
                new Route(Direction.S, List.of(Position.of(File.D, Rank.THREE))),
                // North
                new Route(Direction.N, List.of(Position.of(File.D, Rank.FIVE))),
                // South-East
                new Route(Direction.SE, List.of(Position.of(File.E, Rank.THREE))),
                // East
                new Route(Direction.E, List.of(Position.of(File.E, Rank.FOUR))),
                // North-East
                new Route(Direction.NE, List.of(Position.of(File.E, Rank.FIVE)))
        );
        assertThat(routes).isEqualTo(expectedRoutes);
    }
}
