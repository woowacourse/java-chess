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
        Rook rook1 = new Rook("R", "BLACK", Point.of('a', 8));
        assertThat(Pieces.findPiece('a', 8)).isEqualTo(rook1);
        Rook rook2 = new Rook("R", "BLACK", Point.of('h', 8));
        assertThat(Pieces.findPiece('h', 8)).isEqualTo(rook2);
        Rook rook3 = new Rook("r", "WHITE", Point.of('a', 1));
        assertThat(Pieces.findPiece('a', 1)).isEqualTo(rook3);
        Rook rook4 = new Rook("r", "WHITE", Point.of('h', 1));
        assertThat(Pieces.findPiece('h', 1)).isEqualTo(rook4);
    }
}
