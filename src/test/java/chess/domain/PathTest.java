package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PathTest {

    @DisplayName("출발지와 방향을 전달받아 단일 칸 이동 경로를 만들 수 있다. (경로를 이루는 칸이 하나 뿐이다.)")
    @Test
    void 단일_칸_이동경로_생성() {
        Path path = Path.ofSinglePath(Position.of(1, 1), Direction.NORTH);

        assertThat(path.positions()).containsExactlyInAnyOrder(Position.of(1, 2));
    }

    @DisplayName("출발지와 방향을 전달받아 다중 칸 이동 경로를 만들 수 있다. (경로를 이루는 칸이 여러가지일 수 있다.)")
    @Test
    void 다중_칸_이동경로_생성() {
        Path path = Path.ofMultiPath(Position.of(1, 1), Direction.NORTH);

        assertThat(path.positions()).containsExactly(
                Position.of(1, 2), Position.of(1, 3), Position.of(1, 4), Position.of(1, 5), Position.of(1, 6),
                Position.of(1, 7), Position.of(1, 8));
    }

    @DisplayName("폰의 경우, 첫 이동 시 2칸을 이동하는 이동 경로를 만들 수 있다.")
    @Test
    void 폰_시작_이동경로_생성() {
        Path path = Path.ofPawnStartPath(Position.of(1, 2), Direction.NORTH);

        assertThat(path.positions()).containsExactly(Position.of(1, 3), Position.of(1, 4));
    }

}
