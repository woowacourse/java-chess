package model.piece.role;

import static model.direction.Direction.E;
import static model.direction.Direction.N;
import static model.direction.Direction.NE;
import static model.direction.Direction.NW;
import static model.direction.Direction.S;
import static model.direction.Direction.SE;
import static model.direction.Direction.SW;
import static model.direction.Direction.W;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import model.piece.Color;
import model.position.Position;
import model.position.Route;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class KingTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("Knight의 현재 위치에서 목적지 전까지의 경로인 Route를 반환한다.")
    void findDirectRoute_ReturnsPossiblePositions_WhenCurrentPositionIsGiven(Color color) {
        Role king = King.from(color);
        Position initialPosition = Position.of(4, 4);

        Position destN = Position.of(4, 5);
        Position destNE = Position.of(5, 5);
        Position destE = Position.of(5, 4);
        Position destSE = Position.of(5, 3);
        Position destS = Position.of(4, 3);
        Position destSW = Position.of(3, 3);
        Position destW = Position.of(3, 4);
        Position destNW = Position.of(3, 5);

        Route actualRouteN = king.findDirectRoute(initialPosition, destN);
        Route actualRouteNE = king.findDirectRoute(initialPosition, destNE);
        Route actualRouteE = king.findDirectRoute(initialPosition, destE);
        Route actualRouteSE = king.findDirectRoute(initialPosition, destSE);
        Route actualRouteS = king.findDirectRoute(initialPosition, destS);
        Route actualRouteSW = king.findDirectRoute(initialPosition, destSW);
        Route actualRouteW = king.findDirectRoute(initialPosition, destW);
        Route actualRouteNW = king.findDirectRoute(initialPosition, destNW);

        Route expectedRouteN = new Route(N, List.of(Position.of(4, 5)));
        Route expectedRouteNE = new Route(NE, List.of(Position.of(5, 5)));
        Route expectedRouteE = new Route(E, List.of(Position.of(5, 4)));
        Route expectedRouteSE = new Route(SE, List.of(Position.of(5, 3)));
        Route expectedRouteS = new Route(S, List.of(Position.of(4, 3)));
        Route expectedRouteSW = new Route(SW, List.of(Position.of(3, 3)));
        Route expectedRouteW = new Route(W, List.of(Position.of(3, 4)));
        Route expectedRouteNW = new Route(NW, List.of(Position.of(3, 5)));

        assertAll(() -> {
            assertEquals(expectedRouteN, actualRouteN);
            assertEquals(expectedRouteNE, actualRouteNE);
            assertEquals(expectedRouteE, actualRouteE);
            assertEquals(expectedRouteSE, actualRouteSE);
            assertEquals(expectedRouteS, actualRouteS);
            assertEquals(expectedRouteSW, actualRouteSW);
            assertEquals(expectedRouteW, actualRouteW);
            assertEquals(expectedRouteNW, actualRouteNW);
        });
    }
}
