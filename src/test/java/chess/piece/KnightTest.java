package chess.piece;

import chess.Point;
import chess.piece.Knight;
import chess.piece.Pieces;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KnightTest {
    @DisplayName("Knight 생성")
    @Test
    public void create() {
        Knight knight7 = new Knight("N", "BLACK", Point.valueOf(0, 1));
        assertThat(Pieces.findPiece(0, 1)).isEqualTo(knight7);
        Knight knight2 = new Knight("N", "BLACK", Point.valueOf(0, 6));
        assertThat(Pieces.findPiece(0, 6)).isEqualTo(knight2);
        Knight knight3 = new Knight("n", "WHITE", Point.valueOf(7, 1));
        assertThat(Pieces.findPiece(7, 1)).isEqualTo(knight3);
        Knight knight4 = new Knight("n", "WHITE", Point.valueOf(7, 6));
        assertThat(Pieces.findPiece(7, 6)).isEqualTo(knight4);
    }
}
