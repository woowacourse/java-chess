package chess.domain.piece;

import chess.domain.board.Tile;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BishopTest {
    Piece bishop = new Bishop(PieceColor.WHITE);

    @Test
    void 비숍_NE방향_이동_검사() {
        assertThat(bishop.pathOf(Tile.of("d4"), Tile.of("f6"), false))
                .isEqualTo(Arrays.asList(Tile.of("e5")));
    }

    @Test
    void 비숍_SE방향_이동_검사() {
        assertThat(bishop.pathOf(Tile.of("d4"), Tile.of("e3"), false))
                .isEqualTo(Arrays.asList());
    }

    @Test
    void 비숍_SW방향_이동_검사() {
        assertThat(bishop.pathOf(Tile.of("d4"), Tile.of("c3"), false))
                .isEqualTo(Arrays.asList());
    }

    @Test
    void 비숍_NW방향_이동_검사() {
        assertThat(bishop.pathOf(Tile.of("d4"), Tile.of("b6"), false))
                .isEqualTo(Arrays.asList(Tile.of("c5")));
    }

    @Test
    void 비숍_위쪽_이동_에러() {
        assertThrows(RuntimeException.class, () ->
                bishop.pathOf(Tile.of("d4"), Tile.of("d5"), false)
        );
    }
}