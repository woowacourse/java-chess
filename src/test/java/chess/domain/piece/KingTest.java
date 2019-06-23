package chess.domain.piece;

import chess.domain.board.Tile;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KingTest {
    Piece king = new King(PieceColor.WHITE);

    @Test
    void 킹_오른쪽_이동_검사() {
        assertThat(king.pathOf(Tile.of("d4"), Tile.of("e4"), false))
                .isEqualTo(Arrays.asList());
    }

    @Test
    void 킹_위쪽_이동_검사() {
        assertThat(king.pathOf(Tile.of("d4"), Tile.of("d5"), false))
                .isEqualTo(Arrays.asList());
    }

    @Test
    void 킹_왼쪽_이동_검사() {
        assertThat(king.pathOf(Tile.of("d4"), Tile.of("c4"), false))
                .isEqualTo(Arrays.asList());
    }

    @Test
    void 킹_아래쪽_이동_검사() {
        assertThat(king.pathOf(Tile.of("d4"), Tile.of("d3"), false))
                .isEqualTo(Arrays.asList());
    }

    @Test
    void 킹_NE방향_이동_검사() {
        assertThat(king.pathOf(Tile.of("d4"), Tile.of("e5"), false))
                .isEqualTo(Arrays.asList());
    }

    @Test
    void 킹_SE방향_이동_검사() {
        assertThat(king.pathOf(Tile.of("d4"), Tile.of("e3"), false))
                .isEqualTo(Arrays.asList());
    }

    @Test
    void 킹_SW방향_이동_검사() {
        assertThat(king.pathOf(Tile.of("d4"), Tile.of("c3"), false))
                .isEqualTo(Arrays.asList());
    }

    @Test
    void 킹_NW방향_이동_검사() {
        assertThat(king.pathOf(Tile.of("d4"), Tile.of("c5"), false))
                .isEqualTo(Arrays.asList());
    }

    @Test
    void 킹_2칸이동_이동_에러() {
        assertThrows(RuntimeException.class, () ->
                king.pathOf(Tile.of("d4"), Tile.of("d6"), false)
        );
    }
}