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
    public void 움직여야_할_때_움직이는지_확인() {
        assertThat(queen.canMove(Positions.matchWith(2, 4))).isTrue();
        assertThat(queen.canMove(Positions.matchWith(3, 4))).isTrue();
        assertThat(queen.canMove(Positions.matchWith(3, 3))).isTrue();
    }

    @Test
    public void 움직이지_말아야_할_때_안_움직이는지_확인() {
        assertThat(queen.canMove(Positions.matchWith(3, 5))).isFalse();
        assertThat(queen.canMove(Positions.matchWith(1, 1))).isFalse();
    }
}