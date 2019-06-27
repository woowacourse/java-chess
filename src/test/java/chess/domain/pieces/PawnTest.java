package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.position.Positions;
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
        firstPlaceWhite = new Pawn(Positions.matchWith(3, 2), Team.WHITE);
        firstPlaceBlack = new Pawn(Positions.matchWith(3, 7), Team.BLACK);
        white = new Pawn(Positions.matchWith(3, 4), Team.WHITE);
        black = new Pawn(Positions.matchWith(3, 5), Team.BLACK);
    }

    @Test
    public void 움직여야_할_때_움직이는지_확인() {
        assertThat(firstPlaceWhite.canMove(Positions.matchWith(3, 3))).isTrue();
        assertThat(firstPlaceWhite.canMove(Positions.matchWith(3, 4))).isTrue();

        assertThat(firstPlaceBlack.canMove(Positions.matchWith(3, 6))).isTrue();
        assertThat(firstPlaceBlack.canMove(Positions.matchWith(3, 5))).isTrue();

        assertThat(white.canMove(Positions.matchWith(3, 5))).isTrue();
        assertThat(black.canMove(Positions.matchWith(3, 4))).isTrue();
    }

    @Test
    public void 움직이지_말아야_할_때_안_움직이는지_확인() {
        assertThat(firstPlaceWhite.canMove(Positions.matchWith(4, 2))).isFalse();
        assertThat(firstPlaceWhite.canMove(Positions.matchWith(3, 5))).isFalse();
        assertThat(firstPlaceWhite.canMove(Positions.matchWith(3, 1))).isFalse();

        assertThat(firstPlaceBlack.canMove(Positions.matchWith(4, 7))).isFalse();
        assertThat(firstPlaceBlack.canMove(Positions.matchWith(3, 4))).isFalse();
        assertThat(firstPlaceBlack.canMove(Positions.matchWith(3, 8))).isFalse();

        assertThat(white.canMove(Positions.matchWith(3, 6))).isFalse();
        assertThat(white.canMove(Positions.matchWith(3, 3))).isFalse();
        assertThat(white.canMove(Positions.matchWith(4, 4))).isFalse();

        assertThat(black.canMove(Positions.matchWith(3, 3))).isFalse();
        assertThat(black.canMove(Positions.matchWith(3, 6))).isFalse();
        assertThat(black.canMove(Positions.matchWith(4, 5))).isFalse();
    }
}