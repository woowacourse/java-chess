package chess.piece;

import chess.Point;
import chess.piece.King;
import chess.piece.Pieces;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {
    @DisplayName("King 생성")
    @Test
    public void create() {
        King king1 = new King("K", "BLACK", Point.valueOf(4, 0));
        assertThat(Pieces.findPiece(4, 0)).isEqualTo(king1);
        King king2 = new King("k", "WHITE", Point.valueOf(4, 7));
        assertThat(Pieces.findPiece(4, 7)).isEqualTo(king2);
    }
}
