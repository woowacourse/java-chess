package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.path.Direction;
import chess.domain.path.MovablePaths;
import chess.domain.path.Path;
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
            List.of(Path.ofMultiPath(Position.of(6, 6), Direction.NORTH_EAST, Position.max()),
                Path.ofMultiPath(Position.of(6, 6), Direction.SOUTH_WEST, Position.max())));

        assertThatThrownBy(() -> movablePaths.findPathContainingPosition(Position.of(1, 5)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("해당 말이 갈 수 없는 위치입니다.");
    }

    @DisplayName("이동 가능한 경로들 중, 도착지가 있는 경로를 찾아 반환한다.")
    @Test
    void 경로에_도착지_있으면_반환() {
        MovablePaths movablePaths = new MovablePaths(
            List.of(Path.ofMultiPath(Position.of(6, 6), Direction.NORTH_EAST, Position.max()),
                Path.ofMultiPath(Position.of(6, 6), Direction.SOUTH_WEST, Position.max())));

        Path path = movablePaths.findPathContainingPosition(Position.of(1, 1));

        assertThat(path.findIndexByPosition(Position.of(1, 1))).isEqualTo(4);
    }

}