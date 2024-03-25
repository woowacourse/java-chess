package model.position;

import model.direction.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RouteTest {

    @Test
    @DisplayName("출발 지점과 도착 지점을 제외한 Route를 반환한다.")
    void subRoute_ShouldReturnSubListOfHoleRoute_WhenGivenDestination() {
        // When
        List<Position> holePositions = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            holePositions.add(Position.of(File.A, rank));
        }
        Route initialRoute = new Route(Direction.N, holePositions);
        initialRoute.removeSourceAndTarget();

        List<Position> subPositions = new ArrayList<>();
        subPositions.add(Position.of(File.A, Rank.TWO));
        subPositions.add(Position.of(File.A, Rank.THREE));
        subPositions.add(Position.of(File.A, Rank.FOUR));
        subPositions.add(Position.of(File.A, Rank.FIVE));
        subPositions.add(Position.of(File.A, Rank.SIX));
        subPositions.add(Position.of(File.A, Rank.SEVEN));

        assertThat(initialRoute).isEqualTo(new Route(Direction.N, subPositions));
    }
}
