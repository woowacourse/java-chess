package model.piece.role;

import static model.direction.Direction.N;
import static model.direction.Direction.S;
import static model.direction.Direction.E;
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

class RookTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("Rook의 현재 위치에서 목적지 전까지의 경로인 Route를 반환한다.")
    void findDirectRoute_ReturnsPossiblePositions_WhenCurrentPositionIsGiven(Color color) {
        Role queen = Queen.from(color);
        Position initialPosition = Position.of(4, 4);

        Position destN = Position.of(4, 8);
        Position destS = Position.of(4, 1);
        Position destE = Position.of(8, 4);
        Position destW = Position.of(1, 4);

        Route actualRouteN = queen.findDirectRoute(initialPosition, destN);
        Route actualRouteS = queen.findDirectRoute(initialPosition, destS);
        Route actualRouteE = queen.findDirectRoute(initialPosition, destE);
        Route actualRouteW = queen.findDirectRoute(initialPosition, destW);


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

        assertAll(() -> {
            assertEquals(expectedRouteN, actualRouteN);
            assertEquals(expectedRouteS, actualRouteS);
            assertEquals(expectedRouteE, actualRouteE);
            assertEquals(expectedRouteW, actualRouteW);
        });
    }
}
