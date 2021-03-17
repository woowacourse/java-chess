package chess;

import chess.piece.Bishop;
import chess.piece.Pieces;
import chess.piece.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {
    @DisplayName("Bishop 생성")
    @Test
    public void create() {
        Bishop bishop1 = new Bishop("B", "BLACK", Point.of('c', 8));
        assertThat(Pieces.findPiece('c', 8)).isEqualTo(bishop1);
        Bishop bishop2 = new Bishop("B", "BLACK", Point.of('f', 8));
        assertThat(Pieces.findPiece('f', 8)).isEqualTo(bishop2);
        Bishop bishop3 = new Bishop("b", "WHITE", Point.of('c', 1));
        assertThat(Pieces.findPiece('c', 1)).isEqualTo(bishop3);
        Bishop bishop4 = new Bishop("b", "WHITE", Point.of('f', 1));
        assertThat(Pieces.findPiece('f', 1)).isEqualTo(bishop4);
    }
}
