package chess.piece;

import chess.Point;
import chess.piece.Pieces;
import chess.piece.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {
    @DisplayName("Queen 생성")
    @Test
    public void create() {
        Queen queen1 = new Queen("Q", "BLACK", Point.valueOf(3, 0));
        assertThat(Pieces.findPiece(3, 0)).isEqualTo(queen1);
        Queen queen2 = new Queen("q", "WHITE", Point.valueOf(3, 7));
        assertThat(Pieces.findPiece(3, 7)).isEqualTo(queen2);
    }
}
