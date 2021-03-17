package chess;

import chess.piece.King;
import chess.piece.Pieces;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {
    @DisplayName("King 생성")
    @Test
    public void create() {
        King king1 = new King("K", "BLACK", Point.of('e', 8));
        assertThat(Pieces.findPiece('e', 8)).isEqualTo(king1);
        King king2 = new King("k", "WHITE", Point.of('e', 1));
        assertThat(Pieces.findPiece('e', 1)).isEqualTo(king2);
    }
}
