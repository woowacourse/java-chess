package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.location.Location;
import chess.domain.team.Team;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    private Knight knight;

    @BeforeEach
    void setUp() {
        knight = Knight.of(Location.of(2, 1), Team.WHITE);
    }

    @DisplayName("나이트는 대각선으로 한 칸 이동 후 수평 혹은 수직으로 한 칸 이동한다.")
    @Test
    void movable_test() {
        // given, when
        Location diagonalVerticalTarget = Location.of(3, 3);
        Location diagonalHorizontalTarget = Location.of(4, 2);

        // then
        assertThat(knight.isMovable(diagonalVerticalTarget)).isTrue();
        assertThat(knight.isMovable(diagonalHorizontalTarget)).isTrue();
    }

    @DisplayName("나이트는 대각선, 수평으로 한 칸씩 움직이는 경로가 아니면 이동할 수 없다.")
    @Test
    void nonMovable_test() {
        // given, when
        Location nonMovableTarget = Location.of(2, 3);

        // then
        assertThat(knight.isMovable(nonMovableTarget)).isFalse();
    }

    @DisplayName("나이트는 경로가 없습니다.")
    @Test
    void findPath() {
        // given
        Location target = Location.of(3, 3);

        // when
        List<Location> pathToTarget = knight.findPath(target);

        // then
        assertThat(pathToTarget).isEmpty();
    }
}
