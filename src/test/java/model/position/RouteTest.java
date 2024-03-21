package model.position;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RouteTest {

    @Test
    @DisplayName("출발 지점부터 도착 지점 이전까지의 Route를 반환한다.")
    void subRoute_ShouldReturnSubListOfHoleRoute_WhenGivenDestination() {
        // When
        List<Position> holePositions = new ArrayList<>();
        for (int rank = 1; rank <= 8; rank++) {
            holePositions.add(Position.of(1, rank));
        }
        Route initialRoute = new Route(holePositions);

        List<Position> subPositions = new ArrayList<>();
        for (int rank = 1; rank < 4; rank++) {
            subPositions.add(Position.of(1, rank));
        }

        Route subRoute = initialRoute.subRoute(Position.of(1, 4));

        assertEquals(subPositions, subRoute.getPositions());
    }
}
