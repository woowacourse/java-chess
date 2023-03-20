package chess.domain.direction;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @Test
    @DisplayName("시작과 출발 사이의 경로를 반환해준다. 좌측 하단에서 우측 상단 방향 (c3 -> g7)")
    void returns_route_from_south_west_north_east() {
        // given
        Position source = Position.from("c3");
        Position destination = Position.from("g7");
        List<Position> expectedResult = List.of(
            Position.from("d4"),
            Position.from("e5"),
            Position.from("f6"));

        // when
        List<Position> result = Direction.getRoute(source, destination);

        // then
        assertThat(result.containsAll(expectedResult)).isTrue();
    }

    @Test
    @DisplayName("시작과 출발 사이의 경로를 반환해준다. 우측 상단에서 좌측 하단 방향 (g7 -> c3)")
    void returns_route_from_north_east_to_south_west() {
        // given
        Position source = Position.from("g7");
        Position destination = Position.from("c3");
        List<Position> expectedResult = List.of(
            Position.from("f6"),
            Position.from("e5"),
            Position.from("d4"));

        // when
        List<Position> result = Direction.getRoute(source, destination);

        // then
        assertThat(result.containsAll(expectedResult)).isTrue();
    }

    @Test
    @DisplayName("시작과 출발 사이의 경로를 반환해준다. 좌측 상단에서 우측 하단 방향 (a8 -> d5)")
    void returns_route_from_north_west_to_south_east() {
        // given
        Position source = Position.from("a8");
        Position destination = Position.from("d5");
        List<Position> expectedResult = List.of(
            Position.from("b7"),
            Position.from("c6"));

        // when
        List<Position> result = Direction.getRoute(source, destination);

        // then
        assertThat(result.containsAll(expectedResult)).isTrue();
    }

    @Test
    @DisplayName("시작과 출발 사이의 경로를 반환해준다. 우측 하단에서 좌측 상단 방향 (d5 -> a8)")
    void returns_route_from_south_east_to_north_west() {
        // given
        Position source = Position.from("d5");
        Position destination = Position.from("a8");
        List<Position> expectedResult = List.of(
            Position.from("c6"),
            Position.from("b7"));

        // when
        List<Position> result = Direction.getRoute(source, destination);

        // then
        assertThat(result.containsAll(expectedResult)).isTrue();
    }

    @Test
    @DisplayName("시작과 출발 사이의 경로를 반환해준다. 아래에서 위 방향 (c3 -> c7)")
    void returns_route_from_south_to_north() {
        // given
        Position source = Position.from("c3");
        Position destination = Position.from("c7");
        List<Position> expectedResult = List.of(
            Position.from("c4"),
            Position.from("c5"),
            Position.from("c6"));

        // when
        List<Position> result = Direction.getRoute(source, destination);

        // then
        assertThat(result.containsAll(expectedResult)).isTrue();
    }

    @Test
    @DisplayName("시작과 출발 사이의 경로를 반환해준다. 위에서 아래 방향 (c7 -> c3)")
    void returns_route_from_north_to_south() {
        // given
        Position source = Position.from("c7");
        Position destination = Position.from("c3");
        List<Position> expectedResult = List.of(
            Position.from("c6"),
            Position.from("c5"),
            Position.from("c4"));

        // when
        List<Position> result = Direction.getRoute(source, destination);

        // then
        assertThat(result.containsAll(expectedResult)).isTrue();
    }

    @Test
    @DisplayName("시작과 출발 사이의 경로를 반환해준다. 좌측에서 우측 (a8 -> d8)")
    void returns_route_from_west_to_east() {
        // given
        Position source = Position.from("a8");
        Position destination = Position.from("d8");
        List<Position> expectedResult = List.of(
            Position.from("b8"),
            Position.from("c8"));

        // when
        List<Position> result = Direction.getRoute(source, destination);

        // then
        assertThat(result.containsAll(expectedResult)).isTrue();
    }

    @Test
    @DisplayName("시작과 출발 사이의 경로를 반환해준다. 우측에서 좌측 (d8 -> a8)")
    void returns_route_from_east_to_west() {
        // given
        Position source = Position.from("d8");
        Position destination = Position.from("a8");
        List<Position> expectedResult = List.of(
            Position.from("b8"),
            Position.from("c8"));

        // when
        List<Position> result = Direction.getRoute(source, destination);

        // then
        assertThat(result.containsAll(expectedResult)).isTrue();
    }
}
