package chess;

import chess.piece.Knight;
import chess.piece.Pieces;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KnightTest {
    @DisplayName("Knight 생성")
    @Test
    public void create() {
        Knight knight1 = new Knight("N", "BLACK", Point.of('b', 8));
        assertThat(Pieces.findPiece('b', 8)).isEqualTo(knight1);
        Knight knight2 = new Knight("N", "BLACK", Point.of('g', 8));
        assertThat(Pieces.findPiece('g', 8)).isEqualTo(knight2);
        Knight knight3 = new Knight("n", "WHITE", Point.of('b', 1));
        assertThat(Pieces.findPiece('b', 1)).isEqualTo(knight3);
        Knight knight4 = new Knight("n", "WHITE", Point.of('g', 1));
        assertThat(Pieces.findPiece('g', 1)).isEqualTo(knight4);
    }
}
