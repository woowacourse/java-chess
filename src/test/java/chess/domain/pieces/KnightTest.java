package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.position.Positions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    private Knight knight;

    @BeforeEach
    public void setUp() {
        knight = new Knight(Positions.matchWith(2, 3), Team.WHITE);
    }

    @Test
    public void 앞으로_하나_오른쪽으로_두_칸_움직이기() {
        assertThat(knight.canMove(Positions.matchWith(4, 4))).isTrue();
    }

    @Test
    public void 앞으로_둘_오른쪽으로_한_칸_움직이기() {
        assertThat(knight.canMove(Positions.matchWith(3, 5))).isTrue();
    }

    @Test
    public void 뒤로_둘_왼쪽으로_한_칸_움직이기() {
        assertThat(knight.canMove(Positions.matchWith(1, 1))).isTrue();
    }

    @Test
    public void 오른쪽으로_한_칸_움직이지_않기() {
        assertThat(knight.canMove(Positions.matchWith(3, 3))).isFalse();
    }

    @Test
    public void 대각선_한_칸_움직이지_않기() {
        assertThat(knight.canMove(Positions.matchWith(1, 2))).isFalse();
    }

    @Test
    public void 앞으로_두_칸_움직이지_않기() {
        assertThat(knight.canMove(Positions.matchWith(2, 5))).isFalse();
    }
}