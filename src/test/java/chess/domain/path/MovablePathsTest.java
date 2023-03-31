package chess.domain.path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class MovablePathsTest {

    @DisplayName("이동 가능한 경로들에 도착지가 없다면 예외가 발생한다")
    @Test
    void 경로에_도착지_없으면_예외() {
        MovablePaths movablePaths = new MovablePaths(
            List.of(Path.ofNoLimitPath(Position.from("F6"), Direction.NORTH_EAST),
                Path.ofNoLimitPath(Position.from("F6"), Direction.SOUTH_WEST)));

        assertThatThrownBy(() -> movablePaths.findPathContainingPosition(Position.from("A5")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("해당 말이 갈 수 없는 위치입니다.");
    }

    @DisplayName("이동 가능한 경로들 중, 도착지가 있는 경로를 찾아 반환한다.")
    @Test
    void 경로에_도착지_있으면_반환() {
        MovablePaths movablePaths = new MovablePaths(
            List.of(Path.ofNoLimitPath(Position.from("F6"), Direction.NORTH_EAST),
                Path.ofNoLimitPath(Position.from("F6"), Direction.SOUTH_WEST)));

        Path path = movablePaths.findPathContainingPosition(Position.from("A1"));

        assertThat(path.findIndexByPosition(Position.from("A1"))).isEqualTo(4);
    }

}