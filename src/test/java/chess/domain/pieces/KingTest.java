package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.position.PositionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {
    private King king;

    @BeforeEach
    void setUp() {
        king = new King(PositionManager.getMatchPosition(2, 3), Team.WHITE);
    }

    @Test
    public void 움직여야_할_때_움직이는지_확인한다() {
        assertThat(king.canMove(PositionManager.getMatchPosition(2, 4))).isTrue();
        assertThat(king.canMove(PositionManager.getMatchPosition(3, 3))).isTrue();
        assertThat(king.canMove(PositionManager.getMatchPosition(3, 4))).isTrue();
    }

    @Test
    public void 움직이지_말아야_할_때_안_움직이는지_확인한다() {
        assertThat(king.canMove(PositionManager.getMatchPosition(4, 3))).isFalse();
        assertThat(king.canMove(PositionManager.getMatchPosition(4, 5))).isFalse();
    }
}