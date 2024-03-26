package model.direction;

import model.position.File;
import model.position.Position;
import model.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RouteTest {

    @Test
    @DisplayName("출발 지점 부터 도착 지점까지의 Route를 반환한다.")
    void subRoute_ShouldReturnSubListOfHoleRoute_WhenGivenDestination() {
        // When
        List<Position> holePositions = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            holePositions.add(Position.of(File.A, rank));
        }
        Route route = new Route(Direction.N, holePositions);

        List<Position> subPositions = new ArrayList<>();
        subPositions.add(Position.of(File.A, Rank.ONE));
        subPositions.add(Position.of(File.A, Rank.TWO));
        subPositions.add(Position.of(File.A, Rank.THREE));
        subPositions.add(Position.of(File.A, Rank.FOUR));
        subPositions.add(Position.of(File.A, Rank.FIVE));
        subPositions.add(Position.of(File.A, Rank.SIX));
        subPositions.add(Position.of(File.A, Rank.SEVEN));
        subPositions.add(Position.of(File.A, Rank.EIGHT));

        assertThat(route).isEqualTo(new Route(Direction.N, subPositions));
    }
}
