package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.position.Positions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    private Bishop bishop;

    @BeforeEach
    public void setUp() {
        bishop = new Bishop(Positions.matchWith(2, 3), Team.WHITE);
    }

    @Test
    public void 대각선_한_칸_움직이기() {
        assertThat(bishop.canMove(Positions.matchWith(3, 4))).isTrue();
        assertThat(bishop.canMove(Positions.matchWith(3, 2))).isTrue();
        assertThat(bishop.canMove(Positions.matchWith(1, 2))).isTrue();
    }

    @Test
    public void 대각선_여러_칸_움직이기() {
        assertThat(bishop.canMove(Positions.matchWith(6, 7))).isTrue();
    }

    @Test
    public void 오른쪽으로_움직이지_않기() {
        assertThat(bishop.canMove(Positions.matchWith(3, 3))).isFalse();
    }

    @Test
    public void 뒤로_움직이지_않기() {
        assertThat(bishop.canMove(Positions.matchWith(2, 2))).isFalse();
    }

    @Test
    public void 앞으로_움직이지_않기() {
        assertThat(bishop.canMove(Positions.matchWith(2, 4))).isFalse();
    }
}