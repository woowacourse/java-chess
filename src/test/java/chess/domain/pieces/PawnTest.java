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
    public void 첫_위치일_때_앞으로_한_칸_움직이기() {
        assertThat(firstPlaceWhite.canMove(Positions.matchWith(3, 3))).isTrue();
        assertThat(firstPlaceBlack.canMove(Positions.matchWith(3, 6))).isTrue();
    }

    @Test
    public void 첫_위치일_때_앞으로_두_칸_움직이기() {
        assertThat(firstPlaceWhite.canMove(Positions.matchWith(3, 4))).isTrue();
        assertThat(firstPlaceBlack.canMove(Positions.matchWith(3, 5))).isTrue();
    }

    @Test
    public void 앞으로_한_칸_움직이기() {
        assertThat(white.canMove(Positions.matchWith(3, 5))).isTrue();
        assertThat(black.canMove(Positions.matchWith(3, 4))).isTrue();
    }

    @Test
    public void 첫_위치가_아닐_때_앞으로_두_칸_움직이지_않기() {
        assertThat(white.canMove(Positions.matchWith(3, 6))).isFalse();
        assertThat(black.canMove(Positions.matchWith(3, 3))).isFalse();
    }

    @Test
    public void 뒤로_움직이지_않기() {
        assertThat(white.canMove(Positions.matchWith(3, 3))).isFalse();
        assertThat(white.canMove(Positions.matchWith(3, 2))).isFalse();
        assertThat(black.canMove(Positions.matchWith(3, 6))).isFalse();
        assertThat(black.canMove(Positions.matchWith(3, 7))).isFalse();
    }

    @Test
    public void 왼쪽으로_움직이지_않기() {
        assertThat(white.canMove(Positions.matchWith(2, 4))).isFalse();
        assertThat(white.canMove(Positions.matchWith(1, 4))).isFalse();
        assertThat(black.canMove(Positions.matchWith(2, 5))).isFalse();
        assertThat(black.canMove(Positions.matchWith(1, 5))).isFalse();
    }

    @Test
    public void 오른쪽으로_움직이지_않기() {
        assertThat(white.canMove(Positions.matchWith(4, 4))).isFalse();
        assertThat(white.canMove(Positions.matchWith(5, 4))).isFalse();
        assertThat(black.canMove(Positions.matchWith(4, 5))).isFalse();
        assertThat(black.canMove(Positions.matchWith(5, 5))).isFalse();
    }

    @Test
    public void 대각선_움직이지_않기() {
        assertThat(white.canMove(Positions.matchWith(2, 3))).isFalse();
        assertThat(black.canMove(Positions.matchWith(4, 6))).isFalse();
    }
}