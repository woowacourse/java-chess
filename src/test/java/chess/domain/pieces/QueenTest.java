package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.position.Positions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {
    private Queen queen;

    @BeforeEach
    public void setUp() {
        queen = new Queen(Positions.matchWith(2, 3), Team.WHITE);
    }

    @Test
    public void 앞으로_한_칸_움직이기() {
        assertThat(queen.canMove(Positions.matchWith(2, 4))).isTrue();
    }

    @Test
    public void 뒤로_두_칸_움직이기() {
        assertThat(queen.canMove(Positions.matchWith(2, 1))).isTrue();
    }

    @Test
    public void 대각선_한_칸_움직이기() {
        assertThat(queen.canMove(Positions.matchWith(3, 4))).isTrue();
    }

    @Test
    public void 대각선_다섯_칸_움직이기() {
        assertThat(queen.canMove(Positions.matchWith(7, 8))).isTrue();
    }

    @Test
    public void 앞으로_둘_오른쪽으로_한_칸_움직이지_않기() {
        assertThat(queen.canMove(Positions.matchWith(3, 5))).isFalse();
    }

    @Test
    public void 뒤로_둘_왼쪽으로_한_칸_움직이지_않기() {
        assertThat(queen.canMove(Positions.matchWith(1, 1))).isFalse();
    }
}