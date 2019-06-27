package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.position.Positions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {
    private Rook rook;

    @BeforeEach
    public void setUp() {
        rook = new Rook(Positions.matchWith(2, 3), Team.WHITE);
    }

    @Test
    public void 앞으로_네_칸_움직이기() {
        assertThat(rook.canMove(Positions.matchWith(2, 7))).isTrue();
    }

    @Test
    public void 오른쪽으로_다섯_칸_움직이기() {
        assertThat(rook.canMove(Positions.matchWith(7, 3))).isTrue();
    }

    @Test
    public void 뒤로_두_칸_움직이기() {
        assertThat(rook.canMove(Positions.matchWith(2, 1))).isTrue();
    }

    @Test
    public void 대각선_한_칸_움직이지_않기() {
        assertThat(rook.canMove(Positions.matchWith(3, 4))).isFalse();
    }

    @Test
    public void 대각선_세_칸_움직이지_않기() {
        assertThat(rook.canMove(Positions.matchWith(5, 6))).isFalse();
    }
}