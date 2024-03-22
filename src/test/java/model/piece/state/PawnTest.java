package model.piece.state;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Set;
import model.piece.Color;
import model.position.Position;
import model.position.Route;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("WHITE Pawn의 초기 위치에서 이동할 수 있는 Position을 반환한다.")
    void possibleRoutes_ReturnPossiblePositions_WhenAtInitialPosition_AndColorIsWhite() {
        Role pawn = Pawn.from(Color.WHITE);
        Position initialPosition = Position.of(5, 2);
        Set<Route> routes = pawn.possibleRoutes(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                new Route(List.of(Position.of(5, 3), Position.of(5, 4))),
                new Route(List.of(Position.of(6, 3))),
                new Route(List.of(Position.of(4, 3)))
        );
        assertEquals(expectedRoutes, routes);
    }

    @Test
    @DisplayName("BLACK Pawn의 초기 위치에서 이동할 수 있는 Position을 반환한다.")
    void possibleRoutes_ReturnPossiblePositions_WhenAtInitialPosition_AndColorIsBlack() {
        Role pawn = Pawn.from(Color.BLACK);
        Position initialPosition = Position.of(5, 7);
        Set<Route> routes = pawn.possibleRoutes(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                new Route(List.of(Position.of(5, 6), Position.of(5, 5))),
                new Route(List.of(Position.of(6, 6))),
                new Route(List.of(Position.of(4, 6)))
                );
        assertEquals(expectedRoutes, routes);
    }

    @Test
    @DisplayName("WHITE Pawn이 초기 위치가 아닌 곳에서 이동할 수 있는 Position을 반환한다.")
    void possibleRoutes_ReturnIllegalRoutes_WhenCurrentPositionIsGiven_AndColorIsWhite() {
        Role pawn = Pawn.from(Color.WHITE);
        Position initialPosition = Position.of(5, 3);
        Set<Route> routes = pawn.possibleRoutes(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                new Route(List.of(Position.of(5, 4))),
                new Route(List.of(Position.of(6, 4))),
                new Route(List.of(Position.of(4, 4)))
        );
        assertEquals(expectedRoutes, routes);
    }

    @Test
    @DisplayName("BLACK Pawn의 현재 위치에서 이동할 수 있는 Position을 반환한다.")
    void possibleRoute_ReturnPossibleRoute_WhenCurrentPositionIsGiven_AndColorIsBlack() {
        Role pawn = Pawn.from(Color.BLACK);
        Position initialPosition = Position.of(5, 6);
        Set<Route> routes = pawn.possibleRoutes(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                new Route(List.of(Position.of(5, 5))),
                new Route(List.of(Position.of(6, 5))),
                new Route(List.of(Position.of(4, 5)))
                );

        assertEquals(expectedRoutes, routes);
    }
}
