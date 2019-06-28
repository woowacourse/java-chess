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
    public void 움직여야_할_때_움직이는지_확인() {
        assertThat(knight.canMove(Positions.matchWith(4, 4))).isTrue();
        assertThat(knight.canMove(Positions.matchWith(3, 5))).isTrue();
        assertThat(knight.canMove(Positions.matchWith(1, 1))).isTrue();
    }

    @Test
    public void 움직이지_말아야_할_때_안_움직이는지_확인() {
        assertThat(knight.canMove(Positions.matchWith(3, 3))).isFalse();
        assertThat(knight.canMove(Positions.matchWith(1, 2))).isFalse();
        assertThat(knight.canMove(Positions.matchWith(2, 5))).isFalse();
    }
}