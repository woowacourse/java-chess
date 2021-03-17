package chess.piece;

import chess.Point;
import chess.piece.Pieces;
import chess.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {
    @DisplayName("Rook 생성")
    @Test
    public void create() {
        Rook rook1 = new Rook("R", "BLACK", Point.valueOf(0, 0));
        assertThat(Pieces.findPiece(0, 0)).isEqualTo(rook1);
        Rook rook2 = new Rook("R", "BLACK", Point.valueOf(7, 0));
        assertThat(Pieces.findPiece(7, 0)).isEqualTo(rook2);
        Rook rook3 = new Rook("r", "WHITE", Point.valueOf(0, 7));
        assertThat(Pieces.findPiece(0, 7)).isEqualTo(rook3);
        Rook rook4 = new Rook("r", "WHITE", Point.valueOf(7, 7));
        assertThat(Pieces.findPiece(7, 7)).isEqualTo(rook4);
    }
}
