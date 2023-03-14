package techcourse.fp.chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static techcourse.fp.chess.domain.PositionFixtures.A1;
import static techcourse.fp.chess.domain.PositionFixtures.A8;
import static techcourse.fp.chess.domain.PositionFixtures.B3;
import static techcourse.fp.chess.domain.PositionFixtures.H1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @DisplayName("시작점과 도착점이 같은 File에 있으면 룩은 이동이 가능하다.")
    @Test
    void is_movable_in_same_file() {
        final Rook rook = new Rook(Side.BLACK);
        assertThat(rook.isMovable(A1, A8)).isTrue();
    }

    @DisplayName("시작점과 도착점이 같은 Rank에 있으면 룩은 이동이 가능하다.")
    @Test
    void is_movable_in_same_rank() {
        final Rook rook = new Rook(Side.BLACK);
        assertThat(rook.isMovable(A1, H1)).isTrue();
    }

    @DisplayName("시작점과 도착점이 같다면 이동이 불가능하다.")
    @Test
    void is_not_movable_in_same_position() {
        final Rook rook = new Rook(Side.BLACK);
        assertThat(rook.isMovable(A1, A1)).isFalse();
    }

    @DisplayName("시작점과 도착점이 FIle과 Rank가 모두 다르다면 이동이 불가능하다.")
    @Test
    void is_not_movable() {
        final Rook rook = new Rook(Side.BLACK);
        assertThat(rook.isMovable(A1, B3)).isFalse();
    }
}
