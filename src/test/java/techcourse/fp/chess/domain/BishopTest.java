package techcourse.fp.chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static techcourse.fp.chess.domain.PositionFixtures.A1;
import static techcourse.fp.chess.domain.PositionFixtures.A8;
import static techcourse.fp.chess.domain.PositionFixtures.B2;
import static techcourse.fp.chess.domain.PositionFixtures.B3;
import static techcourse.fp.chess.domain.PositionFixtures.H1;
import static techcourse.fp.chess.domain.PositionFixtures.H8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    private Bishop bishop;

    @BeforeEach
    void init() {
         bishop = new Bishop(Side.BLACK);
    }

    @DisplayName("비숍은 행마법상 기울기의 절대값이 1이 아니면 움직일 수 없다.")
    @Test
    void invalid_move() {
        assertThat(bishop.isMovable(B2, B3)).isFalse();
    }

    @DisplayName("비숍은 행마법상 우상향 방향으로 움직일 수 있다.(기울기 1)")
    @Test
    void movable_to_north_right() {
        assertThat(bishop.isMovable(A1, H8)).isTrue();
    }

    @DisplayName("비숍은 행마법상 우하향 방향으로 움직일 수 있다.(기울기 -1)")
    @Test
    void movable_to_south_right() {
        assertThat(bishop.isMovable(A8, H1)).isTrue();
    }
}
