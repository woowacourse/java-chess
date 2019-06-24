package chess.domain.piece;

import chess.domain.board.Tile;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static chess.domain.piece.PieceType.ROOK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RookTest {
    Piece rook = ROOK.generate(PieceColor.WHITE);

    @Test
    void 룩_오른쪽_이동_검사() {
        assertThat(rook.pathOf(Tile.of("d4"), Tile.of("f4"), false))
                .isEqualTo(Arrays.asList(Tile.of("e4")));
    }

    @Test
    void 룩_위쪽_이동_검사() {
        assertThat(rook.pathOf(Tile.of("d4"), Tile.of("d8"), false))
                .isEqualTo(Arrays.asList(Tile.of("d5"), Tile.of("d6"), Tile.of("d7")));
    }

    @Test
    void 룩_왼쪽_이동_검사() {
        assertThat(rook.pathOf(Tile.of("d4"), Tile.of("c4"), false))
                .isEqualTo(Arrays.asList());
    }

    @Test
    void 룩_아래쪽_이동_검사() {
        assertThat(rook.pathOf(Tile.of("d4"), Tile.of("d1"), false))
                .isEqualTo(Arrays.asList(Tile.of("d3"), Tile.of("d2")));
    }

    @Test
    void 룩_대각선_전진_에러() {
        assertThrows(RuntimeException.class, () ->
                rook.pathOf(Tile.of("d4"), Tile.of("e5"), false)
        );
    }
}