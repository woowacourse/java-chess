package chess.piece;

import chess.Point;
import chess.piece.Bishop;
import chess.piece.Pieces;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {
    @DisplayName("Bishop 생성")
    @Test
    public void create() {
        Bishop bishop1 = new Bishop("B", "BLACK", Point.valueOf(2, 0));
        assertThat(Pieces.findPiece(2, 0)).isEqualTo(bishop1);
        Bishop bishop2 = new Bishop("B", "BLACK", Point.valueOf(5, 0));
        assertThat(Pieces.findPiece(5, 0)).isEqualTo(bishop2);
        Bishop bishop3 = new Bishop("b", "WHITE", Point.valueOf(2, 7));
        assertThat(Pieces.findPiece(2, 7)).isEqualTo(bishop3);
        Bishop bishop4 = new Bishop("b", "WHITE", Point.valueOf(5, 7));
        assertThat(Pieces.findPiece(5, 7)).isEqualTo(bishop4);
    }
}
