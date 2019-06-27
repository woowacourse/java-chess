package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.position.Positions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {
    private King king;

    @BeforeEach
    void setUp() {
        king = new King(Positions.matchWith(2, 3), Team.WHITE);
    }

    @Test
    public void 앞으로_한_칸_움직이기() {
        assertThat(king.canMove(Positions.matchWith(2, 4))).isTrue();
    }

    @Test
    public void 오른쪽으로_한_칸_움직이기() {
        assertThat(king.canMove(Positions.matchWith(3, 3))).isTrue();
    }

    @Test
    public void 대각선_한_칸_움직이기() {
        assertThat(king.canMove(Positions.matchWith(3, 4))).isTrue();
    }

    @Test
    public void 오른쪽으로_두_칸_움직이지_않기() {
        assertThat(king.canMove(Positions.matchWith(4, 3))).isFalse();
    }

    @Test
    public void 대각선_두_칸_움직이지_않기() {
        assertThat(king.canMove(Positions.matchWith(4, 5))).isFalse();
    }
}