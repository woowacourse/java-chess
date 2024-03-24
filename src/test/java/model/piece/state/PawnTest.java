package model.piece.state;

import model.direction.Direction;
import model.piece.Color;
import model.position.Position;
import model.position.Route;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @Test
    @DisplayName("WHITE Pawn의 초기 위치에서 이동할 수 있는 모든 경로(Route)를 반환한다.")
    void possiblePositions_ReturnPossiblePositions_WhenInitialPositionIsGiven_AndColorIsWhite() {
        Role pawn = new Pawn(Color.WHITE);
        Position initialPosition = Position.of(5, 2);
        Set<Route> routes = pawn.findPossibleAllRoute(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                new Route(Direction.N, List.of(Position.of(5, 3), Position.of(5, 4)))
        );

        assertThat(routes).containsExactlyInAnyOrderElementsOf(expectedRoutes);
    }

    @Test
    @DisplayName("WHITE Pawn의 초기 위치가 아닌 위치에서 이동할 수 있는 모든 경로(Route)를 반환한다.")
    void possiblePositions_ReturnPossiblePositions_WhenCurrentPositionIsGiven_AndColorIsWhite() {
        Role pawn = new Pawn(Color.WHITE);
        Position initialPosition = Position.of(5, 3);
        Set<Route> routes = pawn.findPossibleAllRoute(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                new Route(Direction.N, List.of(Position.of(5, 4))),
                new Route(Direction.NE, List.of(Position.of(6, 4))),
                new Route(Direction.NW, List.of(Position.of(4, 4)))
        );

        assertThat(routes).containsExactlyInAnyOrderElementsOf(expectedRoutes);
    }

    @Test
    @DisplayName("BLACK Pawn의 초기 위치에서 이동할 수 있는 Position을 반환한다.")
    void possiblePositions_ReturnPossiblePositions_WhenInitialPositionIsGiven_AndColorIsBlack() {
        Role pawn = new Pawn(Color.BLACK);
        Position initialPosition = Position.of(5, 7);
        Set<Route> routes = pawn.findPossibleAllRoute(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                new Route(Direction.S, List.of(Position.of(5, 6), Position.of(5, 5)))
        );

        assertThat(routes).containsExactlyInAnyOrderElementsOf(expectedRoutes);
    }

    @Test
    @DisplayName("BLACK Pawn의 초기 위치가 아닌 위치에서 이동할 수 있는 Position을 반환한다.")
    void possiblePositions_ReturnPossiblePositions_WhenCurrentPositionIsGiven_AndColorIsBlack() {
        Role pawn = new Pawn(Color.BLACK);
        Position initialPosition = Position.of(5, 6);
        Set<Route> routes = pawn.findPossibleAllRoute(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                new Route(Direction.S, List.of(Position.of(5, 5))),
                new Route(Direction.SE, List.of(Position.of(6, 5))),
                new Route(Direction.SW, List.of(Position.of(4, 5)))
        );

        assertThat(routes).containsExactlyInAnyOrderElementsOf(expectedRoutes);
    }
}
