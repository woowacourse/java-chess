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
    public void 움직여야_할_때_움직이는지_확인() {
        assertThat(rook.canMove(Positions.matchWith(2, 7))).isTrue();
        assertThat(rook.canMove(Positions.matchWith(7, 3))).isTrue();
        assertThat(rook.canMove(Positions.matchWith(2, 1))).isTrue();
    }

    @Test
    public void 움직이지_말아야_할_때_안_움직이는지_확인() {
        assertThat(rook.canMove(Positions.matchWith(3, 4))).isFalse();
        assertThat(rook.canMove(Positions.matchWith(5, 6))).isFalse();
    }
}