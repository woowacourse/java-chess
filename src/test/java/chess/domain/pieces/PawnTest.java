package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.position.PositionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    private Pawn firstPlaceWhite;
    private Pawn firstPlaceBlack;
    private Pawn white;
    private Pawn black;

    @BeforeEach
    public void setUp() {
        firstPlaceWhite = new Pawn(PositionManager.getMatchPosition(3, 2), Team.WHITE);
        firstPlaceBlack = new Pawn(PositionManager.getMatchPosition(3, 7), Team.BLACK);
        white = new Pawn(PositionManager.getMatchPosition(3, 4), Team.WHITE);
        black = new Pawn(PositionManager.getMatchPosition(3, 5), Team.BLACK);
    }

    @Test
    public void 움직여야_할_때_움직이는지_확인() {
        assertThat(firstPlaceWhite.canMove(PositionManager.getMatchPosition(3, 3))).isTrue();
        assertThat(firstPlaceWhite.canMove(PositionManager.getMatchPosition(3, 4))).isTrue();

        assertThat(firstPlaceBlack.canMove(PositionManager.getMatchPosition(3, 6))).isTrue();
        assertThat(firstPlaceBlack.canMove(PositionManager.getMatchPosition(3, 5))).isTrue();

        assertThat(white.canMove(PositionManager.getMatchPosition(3, 5))).isTrue();
        assertThat(black.canMove(PositionManager.getMatchPosition(3, 4))).isTrue();
    }

    @Test
    public void 움직이지_말아야_할_때_안_움직이는지_확인() {
        assertThat(firstPlaceWhite.canMove(PositionManager.getMatchPosition(4, 2))).isFalse();
        assertThat(firstPlaceWhite.canMove(PositionManager.getMatchPosition(3, 5))).isFalse();
        assertThat(firstPlaceWhite.canMove(PositionManager.getMatchPosition(3, 1))).isFalse();

        assertThat(firstPlaceBlack.canMove(PositionManager.getMatchPosition(4, 7))).isFalse();
        assertThat(firstPlaceBlack.canMove(PositionManager.getMatchPosition(3, 4))).isFalse();
        assertThat(firstPlaceBlack.canMove(PositionManager.getMatchPosition(3, 8))).isFalse();

        assertThat(white.canMove(PositionManager.getMatchPosition(3, 6))).isFalse();
        assertThat(white.canMove(PositionManager.getMatchPosition(3, 3))).isFalse();
        assertThat(white.canMove(PositionManager.getMatchPosition(4, 4))).isFalse();

        assertThat(black.canMove(PositionManager.getMatchPosition(3, 3))).isFalse();
        assertThat(black.canMove(PositionManager.getMatchPosition(3, 6))).isFalse();
        assertThat(black.canMove(PositionManager.getMatchPosition(4, 5))).isFalse();
    }
}