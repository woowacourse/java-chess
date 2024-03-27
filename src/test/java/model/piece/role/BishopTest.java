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

class BishopTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("Bishop의 현재 위치에서 이동할 수 있는 모든 경로(Route)를 반환한다.")
    void possiblePositions_ReturnPossiblePositions_WhenCurrentPositionIsGiven(Color color) {
        Role bishop = new Bishop(color);
        Position initialPosition = Position.of(File.D, Rank.FOUR);
        Set<Route> routes = bishop.findPossibleAllRoute(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                // North-West
                new Route(Direction.NW, List.of(Position.of(File.C, Rank.FIVE), Position.of(File.B, Rank.SIX), Position.of(File.A, Rank.SEVEN))),
                // South-West
                new Route(Direction.SW, List.of(Position.of(File.C, Rank.THREE), Position.of(File.B, Rank.TWO), Position.of(File.A, Rank.ONE))),
                // North-East
                new Route(Direction.NE, List.of(Position.of(File.E, Rank.FIVE), Position.of(File.F, Rank.SIX), Position.of(File.G, Rank.SEVEN), Position.of(File.H, Rank.EIGHT))),
                // South-East
                new Route(Direction.SE, List.of(Position.of(File.E, Rank.THREE), Position.of(File.F, Rank.TWO), Position.of(File.G, Rank.ONE))));

        assertThat(routes).isEqualTo(expectedRoutes);
    }
}
