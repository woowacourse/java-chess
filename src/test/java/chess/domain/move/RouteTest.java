package chess.domain.move;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RouteTest {
    @Test
    @DisplayName("Route 생성")
    void create() {
        List<Position> positions = new ArrayList<>();
        positions.add(Position.of(1, 2));
        positions.add(Position.of(2, 3));
        positions.add(Position.of(3, 4));

        assertThat(new Route(positions)).isInstanceOf(Route.class);
    }

    @Test
    @DisplayName("getRoute 테스트")
    void getRoute() {
        List<Position> positions = new ArrayList<>();
        positions.add(Position.of(1, 2));
        positions.add(Position.of(2, 3));
        positions.add(Position.of(3, 4));
        Route route = new Route(positions);

        assertThat(route.getRoute()).isInstanceOf(List.class);
    }

    @Test
    @DisplayName("hasPosition 테스트")
    void hasPosition() {
        List<Position> positions = new ArrayList<>();
        positions.add(Position.of(1, 2));
        positions.add(Position.of(2, 3));
        positions.add(Position.of(3, 4));
        Route route = new Route(positions);

        assertThat(route.hasPosition(Position.of(1, 2))).isTrue();
    }
}
