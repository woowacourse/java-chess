package model.piece.role;

import static model.direction.Direction.N;
import static model.direction.Direction.S;
import static model.direction.Direction.E;
import static model.direction.Direction.W;
import static model.direction.Direction.NE;
import static model.direction.Direction.NW;
import static model.direction.Direction.SE;
import static model.direction.Direction.SW;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import model.piece.Color;
import model.position.Position;
import model.position.Route;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class QueenTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("Queen의 현재 위치에서 목적지 전까지의 경로인 Route를 반환한다.")
    void findDirectRoute_ReturnsPossiblePositions_WhenCurrentPositionIsGiven(Color color) {
        Role queen = Queen.from(color);
        Position initialPosition = Position.of(4, 4);

        Position destN = Position.of(4, 8);
        Position destS = Position.of(4, 1);
        Position destE = Position.of(8, 4);
        Position destW = Position.of(1, 4);
        Position destNE = Position.of(8, 8);
        Position destNW = Position.of(1, 7);
        Position destSE = Position.of(7, 1);
        Position destSW = Position.of(1, 1);

        Route actualRouteN = queen.findDirectRoute(initialPosition, destN);
        Route actualRouteS = queen.findDirectRoute(initialPosition, destS);
        Route actualRouteE = queen.findDirectRoute(initialPosition, destE);
        Route actualRouteW = queen.findDirectRoute(initialPosition, destW);
        Route actualRouteNE = queen.findDirectRoute(initialPosition, destNE);
        Route actualRouteNW = queen.findDirectRoute(initialPosition, destNW);
        Route actualRouteSE = queen.findDirectRoute(initialPosition, destSE);
        Route actualRouteSW = queen.findDirectRoute(initialPosition, destSW);

        Route expectedRouteN = new Route(N, List.of(
                Position.of(4, 5),
                Position.of(4, 6),
                Position.of(4, 7),
                Position.of(4, 8)
        ));
        Route expectedRouteS = new Route(S, List.of(
                Position.of(4, 3),
                Position.of(4, 2),
                Position.of(4, 1)
        ));
        Route expectedRouteE = new Route(E, List.of(
                Position.of(5, 4),
                Position.of(6, 4),
                Position.of(7, 4),
                Position.of(8, 4)
        ));
        Route expectedRouteW = new Route(W, List.of(
                Position.of(3, 4),
                Position.of(2, 4),
                Position.of(1, 4)
        ));
        Route expectedRouteNE = new Route(NE, List.of(
                Position.of(5, 5),
                Position.of(6, 6),
                Position.of(7, 7),
                Position.of(8, 8)
        ));
        Route expectedRouteNW = new Route(NW, List.of(
                Position.of(3, 5),
                Position.of(2, 6),
                Position.of(1, 7)
        ));
        Route expectedRouteSE = new Route(SE, List.of(
                Position.of(5, 3),
                Position.of(6, 2),
                Position.of(7, 1)
        ));
        Route expectedRouteSW = new Route(SW, List.of(
                Position.of(3, 3),
                Position.of(2, 2),
                Position.of(1, 1)
        ));

        assertAll(() -> {
            assertEquals(expectedRouteN, actualRouteN);
            assertEquals(expectedRouteS, actualRouteS);
            assertEquals(expectedRouteE, actualRouteE);
            assertEquals(expectedRouteW, actualRouteW);
            assertEquals(expectedRouteNE, actualRouteNE);
            assertEquals(expectedRouteNW, actualRouteNW);
            assertEquals(expectedRouteSE, actualRouteSE);
            assertEquals(expectedRouteSW, actualRouteSW);
        });
    }
}
