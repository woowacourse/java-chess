package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.move.straight.StraightDirection;

class RouteTest {

    @Test
    @DisplayName("두개의 포인트로부터 루트를 만든다.")
    public void createRouteFromPoints() {
        // given
        Point from = Point.of("a1");
        Point to = Point.of("a2");
        // when
        Route route = new Route(from, to);

        // then
        assertThat(route).isNotNull();
    }

    @Test
    @DisplayName("매개변수로부터 루트를 만든다.")
    public void createRouteFromArguments() {
        // given
        List<String> arguments = List.of("a1", "a2");
        // when
        Route route = Route.of(arguments);
        // then
        assertThat(route).isNotNull();
    }

    @Test
    @DisplayName("매개변수는 2개여야 한다.")
    void throwsExceptionWithInvalidArgumentSize() {
        List<String> arguments = List.of("a2", "a3", "a4");

        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Route.of(arguments));
    }

    @Test
    @DisplayName("가로길이의 차이를 구한다.")
    public void calculateSubtractionOfHorizontal() {
        // given
        Route route = Route.of(List.of("a1", "c1"));
        // when
        int horizontal = route.subtractHorizontal();
        // then
        assertThat(horizontal).isEqualTo(2);
    }

    @Test
    @DisplayName("세로길이의 차를 구한다.")
    public void calculateSubtractionOfVertical() {
        // given
        Route route = Route.of(List.of("a1", "a5"));
        // when
        int vertical = route.subtractVertical();
        // then
        assertThat(vertical).isEqualTo(4);
    }

    @Test
    @DisplayName("방향으로 출발지를 움직인다.")
    public void moveSourceWithDirection() {
        // given
        Route route = Route.of(List.of("a1", "b2"));
        // when
        Route moved = route.moveSource(StraightDirection.NORTH);
        // then
        assertThat(moved.getSource()).isEqualTo(Point.of("a2"));
    }

    @Test
    @DisplayName("도착지에 도착했는지 여부를 확인한다.")
    public void checkIfArrived() {
        // given
        Route route = Route.of(List.of("a1", "a1"));
        // when
        boolean isArrived = route.isArrived();
        // then
        assertThat(isArrived).isTrue();
    }
}