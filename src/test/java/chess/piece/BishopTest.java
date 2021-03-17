package chess.piece;

import chess.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {
    @DisplayName("Bishop 생성")
    @Test
    public void create() {
        Bishop bishop1 = new Bishop("B", "BLACK", Point.valueOf(0, 2));
        assertThat(Pieces.findPiece(0, 2)).isEqualTo(bishop1);
        Bishop bishop2 = new Bishop("B", "BLACK", Point.valueOf(0, 5));
        assertThat(Pieces.findPiece(0, 5)).isEqualTo(bishop2);
        Bishop bishop3 = new Bishop("b", "WHITE", Point.valueOf(7, 2));
        assertThat(Pieces.findPiece(7, 2)).isEqualTo(bishop3);
        Bishop bishop4 = new Bishop("b", "WHITE", Point.valueOf(7, 5));
        assertThat(Pieces.findPiece(7, 5)).isEqualTo(bishop4);
    }
}
