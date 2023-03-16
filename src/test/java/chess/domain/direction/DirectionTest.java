package chess.domain.direction;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @Test
    @DisplayName("시작과 출발 사이의 경로를 반환해준다. 좌측 하단에서 우측 상단 방향 (c3 -> g7)")
    void returns_route_from_south_west_north_east() {
        // given
        String start = "c3";
        String end = "g7";
        List<String> expectedResult = List.of("d4", "e5", "f6");

        // when
        List<String> result = Direction.getRoute(start, end);

        // then
        assertThat(result.containsAll(expectedResult)).isTrue();
    }

    @Test
    @DisplayName("시작과 출발 사이의 경로를 반환해준다. 우측 상단에서 좌측 하단 방향 (g7 -> c3)")
    void returns_route_from_north_east_to_south_west() {
        // given
        String start = "g7";
        String end = "c3";
        List<String> expectedResult = List.of("f6", "e5", "d4");

        // when
        List<String> result = Direction.getRoute(start, end);

        // then
        assertThat(result.containsAll(expectedResult)).isTrue();
    }

    @Test
    @DisplayName("시작과 출발 사이의 경로를 반환해준다. 좌측 상단에서 우측 하단 방향 (a8 -> d5)")
    void returns_route_from_north_west_to_south_east() {
        // given
        String start = "a8";
        String end = "d5";
        List<String> expectedResult = List.of("b7", "c6");

        // when
        List<String> result = Direction.getRoute(start, end);

        // then
        assertThat(result.containsAll(expectedResult)).isTrue();
    }

    @Test
    @DisplayName("시작과 출발 사이의 경로를 반환해준다. 우측 하단에서 좌측 상단 방향 (d5 -> a8)")
    void returns_route_from_south_east_to_north_west() {
        // given
        String start = "d5";
        String end = "a8";
        List<String> expectedResult = List.of("c6", "b7");

        // when
        List<String> result = Direction.getRoute(start, end);

        // then
        assertThat(result.containsAll(expectedResult)).isTrue();
    }

    @Test
    @DisplayName("시작과 출발 사이의 경로를 반환해준다. 아래에서 위 방향 (c3 -> c7)")
    void returns_route_from_south_to_north() {
        // given
        String start = "c3";
        String end = "c7";
        List<String> expectedResult = List.of("c4", "c5", "c6");

        // when
        List<String> result = Direction.getRoute(start, end);

        // then
        assertThat(result.containsAll(expectedResult)).isTrue();
    }

    @Test
    @DisplayName("시작과 출발 사이의 경로를 반환해준다. 위에서 아래 방향 (c7 -> c3)")
    void returns_route_from_north_to_south() {
        // given
        String start = "c7";
        String end = "c3";
        List<String> expectedResult = List.of("c6", "c5", "c4");

        // when
        List<String> result = Direction.getRoute(start, end);

        // then
        assertThat(result.containsAll(expectedResult)).isTrue();
    }

    @Test
    @DisplayName("시작과 출발 사이의 경로를 반환해준다. 좌측에서 우측 (a8 -> d8)")
    void returns_route_from_west_to_east() {
        // given
        String start = "a8";
        String end = "d8";
        List<String> expectedResult = List.of("b8", "c8");

        // when
        List<String> result = Direction.getRoute(start, end);

        // then
        assertThat(result.containsAll(expectedResult)).isTrue();
    }

    @Test
    @DisplayName("시작과 출발 사이의 경로를 반환해준다. 우측에서 좌측 (d8 -> a8)")
    void returns_route_from_east_to_west() {
        // given
        String start = "d8";
        String end = "a8";
        List<String> expectedResult = List.of("b8", "c8");

        // when
        List<String> result = Direction.getRoute(start, end);

        // then
        assertThat(result.containsAll(expectedResult)).isTrue();
    }
}
