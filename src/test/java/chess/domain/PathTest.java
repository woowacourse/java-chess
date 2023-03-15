package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PathTest {

    @DisplayName("출발지와 방향을 전달받아 단일 칸 이동 경로를 만들 수 있다. (경로를 이루는 칸이 하나 뿐이다.)")
    @Test
    void 단일_칸_이동경로_생성() {
        Path path = Path.ofSinglePath(new Position(1, 1), Direction.NORTH);

        assertThat(path.positions()).containsExactlyInAnyOrder(new Position(2, 1));
    }

    @DisplayName("출발지와 방향을 전달받아 다중 칸 이동 경로를 만들 수 있다. (경로를 이루는 칸이 여러가지일 수 있다.)")
    @Test
    void 다중_칸_이동경로_생성() {
        Path path = Path.ofMultiPath(new Position(1, 1), Direction.NORTH);

        assertThat(path.positions()).containsExactly(
                new Position(2, 1), new Position(3, 1), new Position(4, 1), new Position(5, 1), new Position(6, 1),
                new Position(7, 1), new Position(8, 1));
    }
}
