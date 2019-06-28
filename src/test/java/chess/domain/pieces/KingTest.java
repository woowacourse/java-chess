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
    public void 움직여야_할_때_움직이는지_확인() {
        assertThat(king.canMove(Positions.matchWith(2, 4))).isTrue();
        assertThat(king.canMove(Positions.matchWith(3, 3))).isTrue();
        assertThat(king.canMove(Positions.matchWith(3, 4))).isTrue();
    }

    @Test
    public void 움직이지_말아야_할_때_안_움직이는지_확인() {
        assertThat(king.canMove(Positions.matchWith(4, 3))).isFalse();
        assertThat(king.canMove(Positions.matchWith(4, 5))).isFalse();
    }
}