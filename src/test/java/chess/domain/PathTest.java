package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.path.Direction;
import chess.domain.path.Path;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
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
        Path path = Path.ofMultiPath(Position.of(1, 1), Direction.NORTH, Position.max());

        assertThat(path.positions()).containsExactly(
                Position.of(1, 2), Position.of(1, 3), Position.of(1, 4), Position.of(1, 5), Position.of(1, 6),
                Position.of(1, 7), Position.of(1, 8));
    }

    @DisplayName("전달된 최대 개수 만큼의 다중 이동 경로를 만들 수 있다.")
    @Test
    void 크기_제한_다중_이동경로_생성() {
        Path path = Path.ofMultiPath(Position.of(1, 2), Direction.NORTH, 2);

        assertThat(path.positions()).containsExactly(Position.of(1, 3), Position.of(1, 4));
    }

}
