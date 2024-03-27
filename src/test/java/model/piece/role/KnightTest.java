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

class KnightTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("Knight의 현재 위치에서 이동할 수 있는 모든 경로(Route)를 반환한다.")
    void possiblePositions_ReturnsPossiblePositions_WhenCurrentPositionIsGiven(Color color) {
        Role knight = new Knight(color);
        Position initialPosition = Position.of(File.D, Rank.FOUR);
        Set<Route> routes = knight.findPossibleAllRoute(initialPosition);

        Set<Route> expectedRoutes = Set.of(
                // EE_
                new Route(Direction.EEN, List.of(Position.of(File.F, Rank.FIVE))), new Route(Direction.EES, List.of(Position.of(File.F, Rank.THREE))),
                // WW_
                new Route(Direction.WWN, List.of(Position.of(File.B, Rank.FIVE))), new Route(Direction.WWS, List.of(Position.of(File.B, Rank.THREE))),
                // NN_
                new Route(Direction.NNE, List.of(Position.of(File.E, Rank.SIX))), new Route(Direction.NNW, List.of(Position.of(File.C, Rank.SIX))),
                // SS_
                new Route(Direction.SSE, List.of(Position.of(File.E, Rank.TWO))), new Route(Direction.SSW, List.of(Position.of(File.C, Rank.TWO)))
        );

        assertThat(routes).isEqualTo(expectedRoutes);
    }
}
