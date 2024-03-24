package model.piece.role;

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

class BishopTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("Bishop의 현재 위치에서 목적지 전까지의 경로인 Route를 반환한다.")
    void findDirectRoute_ReturnsPossiblePositions_WhenCurrentPositionIsGiven(Color color) {
        Role bishop = Bishop.from(color);
        Position initialPosition = Position.of(4, 4);

        Position destNE = Position.of(8, 8);
        Position destNW = Position.of(1, 7);
        Position destSE = Position.of(7, 1);
        Position destSW = Position.of(2, 2);


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
                Position.of(2, 2)
        ));

        Route actualRouteNE = bishop.findDirectRoute(initialPosition, destNE);
        Route actualRouteNW = bishop.findDirectRoute(initialPosition, destNW);
        Route actualRouteSE = bishop.findDirectRoute(initialPosition, destSE);
        Route actualRouteSW = bishop.findDirectRoute(initialPosition, destSW);

        assertAll(() -> {
            assertEquals(expectedRouteNE, actualRouteNE);
            assertEquals(expectedRouteNW, actualRouteNW);
            assertEquals(expectedRouteSE, actualRouteSE);
            assertEquals(expectedRouteSW, actualRouteSW);
        });
    }
}
