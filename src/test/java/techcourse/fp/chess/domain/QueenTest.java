package techcourse.fp.chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static techcourse.fp.chess.domain.PositionFixtures.A1;
import static techcourse.fp.chess.domain.PositionFixtures.A8;
import static techcourse.fp.chess.domain.PositionFixtures.H1;
import static techcourse.fp.chess.domain.PositionFixtures.H2;
import static techcourse.fp.chess.domain.PositionFixtures.H8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    private Queen queen;

    @BeforeEach
    void init() {
        queen = new Queen(Side.BLACK);
    }

    @DisplayName("시작점과 도착점이 같은 File에 있으면 룩은 이동이 가능하다.")
    @Test
    void is_movable_in_same_file() {
        assertThat(queen.isMovable(A1, A8)).isTrue();
    }

    @DisplayName("시작점과 도착점이 같은 Rank에 있으면 룩은 이동이 가능하다.")
    @Test
    void is_movable_in_same_rank() {
        assertThat(queen.isMovable(A1, H1)).isTrue();
    }
    @DisplayName("비숍은 행마법상 우상향 방향으로 움직일 수 있다.(기울기 1)")
    @Test
    void movable_to_north_right() {
        assertThat(queen.isMovable(A1, H8)).isTrue();
    }

    @DisplayName("비숍은 행마법상 우하향 방향으로 움직일 수 있다.(기울기 -1)")
    @Test
    void movable_to_south_right() {
        assertThat(queen.isMovable(A8, H1)).isTrue();
    }

    @Test
    void fail() {
        assertThat(queen.isMovable(A8, H2)).isFalse();
    }

    @Test
    void fail_samePosition() {
        assertThat(queen.isMovable(A8, A8)).isFalse();
    }
}

