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

class QueenTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("Queen의 현재 위치에서 이동할 수 있는 모든 경로(Route)를 반환한다.")
    void possiblePositions_ReturnsPossiblePositions_WhenCurrentPositionIsGiven(Color color) {
        Role queen = new Queen(color);
        Position initialPosition = Position.of(File.D, Rank.FOUR);
        Set<Route> routes = queen.findPossibleAllRoute(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                // East
                new Route(Direction.E, List.of(Position.of(File.E, Rank.FOUR), Position.of(File.F, Rank.FOUR), Position.of(File.G, Rank.FOUR), Position.of(File.H, Rank.FOUR))),
                // West
                new Route(Direction.W, List.of(Position.of(File.C, Rank.FOUR), Position.of(File.B, Rank.FOUR), Position.of(File.A, Rank.FOUR))),
                // North
                new Route(Direction.N, List.of(Position.of(File.D, Rank.FIVE), Position.of(File.D, Rank.SIX), Position.of(File.D, Rank.SEVEN), Position.of(File.D, Rank.EIGHT))),
                // South
                new Route(Direction.S, List.of(Position.of(File.D, Rank.THREE), Position.of(File.D, Rank.TWO), Position.of(File.D, Rank.ONE))),
                // North-East
                new Route(Direction.NE, List.of(Position.of(File.E, Rank.FIVE), Position.of(File.F, Rank.SIX), Position.of(File.G, Rank.SEVEN), Position.of(File.H, Rank.EIGHT))),
                // South-West
                new Route(Direction.SW, List.of(Position.of(File.C, Rank.THREE), Position.of(File.B, Rank.TWO), Position.of(File.A, Rank.ONE))),
                // South-East
                new Route(Direction.SE, List.of(Position.of(File.E, Rank.THREE), Position.of(File.F, Rank.TWO), Position.of(File.G, Rank.ONE))),
                // North-West
                new Route(Direction.NW, List.of(Position.of(File.C, Rank.FIVE), Position.of(File.B, Rank.SIX), Position.of(File.A, Rank.SEVEN))));

        assertThat(routes).isEqualTo(expectedRoutes);
    }
}
