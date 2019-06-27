package chess.domain.piece;

import chess.domain.board.Tile;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static chess.domain.piece.PieceGenerator.KNIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KnightTest {
    Piece knight = KNIGHT.generate(PieceColor.WHITE);

    @Test
    void 나이트_EEN이동_검사() {
        assertThat(knight.pathOf(Tile.of("d4"), Tile.of("f5"), false))
                .isEqualTo(Arrays.asList());
    }

    @Test
    void 나이트_EES이동_검사() {
        assertThat(knight.pathOf(Tile.of("d4"), Tile.of("f3"), false))
                .isEqualTo(Arrays.asList());
    }

    @Test
    void 나이트_NNW이동_검사() {
        assertThat(knight.pathOf(Tile.of("d4"), Tile.of("c6"), false))
                .isEqualTo(Arrays.asList());
    }

    @Test
    void 나이트_NNE이동_검사() {
        assertThat(knight.pathOf(Tile.of("d4"), Tile.of("e6"), false))
                .isEqualTo(Arrays.asList());
    }

    @Test
    void 나이트_WWN이동_검사() {
        assertThat(knight.pathOf(Tile.of("d4"), Tile.of("b5"), false))
                .isEqualTo(Arrays.asList());
    }

    @Test
    void 나이트_WWS이동_검사() {
        assertThat(knight.pathOf(Tile.of("d4"), Tile.of("b3"), false))
                .isEqualTo(Arrays.asList());
    }

    @Test
    void 나이트_SSE이동_검사() {
        assertThat(knight.pathOf(Tile.of("d4"), Tile.of("e2"), false))
                .isEqualTo(Arrays.asList());
    }

    @Test
    void 나이트_SSW이동_검사() {
        assertThat(knight.pathOf(Tile.of("d4"), Tile.of("c2"), false))
                .isEqualTo(Arrays.asList());
    }

    @Test
    void 나이트_EEN방향_두배_이동_에러() {
        assertThrows(RuntimeException.class, () ->
                knight.pathOf(Tile.of("d4"), Tile.of("h6"), false)
        );
    }

    @Test
    void 나이트_아래쪽_이동_에러() {
        assertThrows(RuntimeException.class, () ->
                knight.pathOf(Tile.of("d4"), Tile.of("d2"), false)
        );
    }
}