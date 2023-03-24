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
        Path path = Path.ofLimitedPath(Position.from("A1"), Direction.NORTH, 1);

        assertThat(path.positions()).containsExactlyInAnyOrder(Position.from("A2"));
    }

    @DisplayName("출발지와 방향을 전달받아 다중 칸 이동 경로를 만들 수 있다. (경로를 이루는 칸이 여러가지일 수 있다.)")
    @Test
    void 다중_칸_이동경로_생성() {
        Path path = Path.ofNoLimitPath(Position.from("A1"), Direction.NORTH);

        assertThat(path.positions()).containsExactly(
            Position.from("A2"), Position.from("A3"), Position.from("A4"),
            Position.from("A5"), Position.from("A6"),
            Position.from("A7"), Position.from("A8"));
    }

    @DisplayName("전달된 최대 개수 만큼의 다중 이동 경로를 만들 수 있다.")
    @Test
    void 크기_제한_다중_이동경로_생성() {
        Path path = Path.ofLimitedPath(Position.from("A2"), Direction.NORTH, 2);

        assertThat(path.positions()).containsExactly(Position.from("A3"), Position.from("A4"));
    }

}
