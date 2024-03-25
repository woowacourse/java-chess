package model.piece.state;

import model.direction.Direction;
import model.piece.Color;
import model.position.File;
import model.position.Position;
import model.position.Rank;
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
        Position initialPosition = Position.of(File.E, Rank.TWO);
        Set<Route> routes = pawn.findPossibleAllRoute(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                new Route(Direction.N, List.of(Position.of(File.E, Rank.THREE), Position.of(File.E, Rank.FOUR)))
        );

        assertThat(routes).isEqualTo(expectedRoutes);
    }

    @Test
    @DisplayName("WHITE Pawn의 초기 위치가 아닌 위치에서 이동할 수 있는 모든 경로(Route)를 반환한다.")
    void possiblePositions_ReturnPossiblePositions_WhenCurrentPositionIsGiven_AndColorIsWhite() {
        Role pawn = new Pawn(Color.WHITE);
        Position initialPosition = Position.of(File.D, Rank.THREE);
        Set<Route> routes = pawn.findPossibleAllRoute(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                new Route(Direction.N, List.of(Position.of(File.D, Rank.FOUR))),
                new Route(Direction.NE, List.of(Position.of(File.E, Rank.FOUR))),
                new Route(Direction.NW, List.of(Position.of(File.C, Rank.FOUR)))
        );

        assertThat(routes).isEqualTo(expectedRoutes);
    }

    @Test
    @DisplayName("BLACK Pawn의 초기 위치에서 이동할 수 있는 Position을 반환한다.")
    void possiblePositions_ReturnPossiblePositions_WhenInitialPositionIsGiven_AndColorIsBlack() {
        Role pawn = new Pawn(Color.BLACK);
        Position initialPosition = Position.of(File.E, Rank.SEVEN);
        Set<Route> routes = pawn.findPossibleAllRoute(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                new Route(Direction.S, List.of(Position.of(File.E, Rank.SIX), Position.of(File.E, Rank.FIVE)))
        );

        assertThat(routes).isEqualTo(expectedRoutes);
    }

    @Test
    @DisplayName("BLACK Pawn의 초기 위치가 아닌 위치에서 이동할 수 있는 Position을 반환한다.")
    void possiblePositions_ReturnPossiblePositions_WhenCurrentPositionIsGiven_AndColorIsBlack() {
        Role pawn = new Pawn(Color.BLACK);
        Position initialPosition = Position.of(File.E, Rank.SIX);
        Set<Route> routes = pawn.findPossibleAllRoute(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                new Route(Direction.S, List.of(Position.of(File.E, Rank.FIVE))),
                new Route(Direction.SE, List.of(Position.of(File.F, Rank.FIVE))),
                new Route(Direction.SW, List.of(Position.of(File.D, Rank.FIVE)))
        );

        assertThat(routes).isEqualTo(expectedRoutes);
    }
}
