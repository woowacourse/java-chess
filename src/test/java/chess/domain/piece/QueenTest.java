package chess.domain.piece;

import chess.domain.board.Tile;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QueenTest {
    Piece queen = new Queen(PieceColor.WHITE);

    @Test
    void 퀸_오른쪽_이동_검사() {
        assertThat(queen.pathOf(Tile.of("d4"), Tile.of("f4"), false))
                .isEqualTo(Arrays.asList(Tile.of("e4")));
    }

    @Test
    void 퀸_위쪽_이동_검사() {
        assertThat(queen.pathOf(Tile.of("d4"), Tile.of("d8"), false))
                .isEqualTo(Arrays.asList(Tile.of("d5"), Tile.of("d6"), Tile.of("d7")));
    }

    @Test
    void 퀸_왼쪽_이동_검사() {
        assertThat(queen.pathOf(Tile.of("d4"), Tile.of("c4"), false))
                .isEqualTo(Arrays.asList());
    }

    @Test
    void 퀸_아래쪽_이동_검사() {
        assertThat(queen.pathOf(Tile.of("d4"), Tile.of("d1"), false))
                .isEqualTo(Arrays.asList(Tile.of("d3"), Tile.of("d2")));
    }

    @Test
    void 퀸_NE방향_이동_검사() {
        assertThat(queen.pathOf(Tile.of("d4"), Tile.of("f6"), false))
                .isEqualTo(Arrays.asList(Tile.of("e5")));
    }

    @Test
    void 퀸_SE방향_이동_검사() {
        assertThat(queen.pathOf(Tile.of("d4"), Tile.of("e3"), false))
                .isEqualTo(Arrays.asList());
    }

    @Test
    void 퀸_SW방향_이동_검사() {
        assertThat(queen.pathOf(Tile.of("d4"), Tile.of("c3"), false))
                .isEqualTo(Arrays.asList());
    }

    @Test
    void 퀸_NW방향_이동_검사() {
        assertThat(queen.pathOf(Tile.of("d4"), Tile.of("b6"), false))
                .isEqualTo(Arrays.asList(Tile.of("c5")));
    }

    @Test
    void 퀸_EES방향_이동_에러() {
        assertThrows(RuntimeException.class, () ->
                queen.pathOf(Tile.of("d4"), Tile.of("f3"), false)
        );
    }
}