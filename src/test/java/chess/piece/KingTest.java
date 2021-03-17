package chess.piece;

import chess.domain.Point;
import chess.domain.piece.King;
import chess.domain.piece.Pieces;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {
    @DisplayName("King 생성")
    @Test
    public void create() {
        King king1 = new King("K", "BLACK", Point.valueOf(0, 4));
        assertThat(Pieces.findPiece(0, 4)).isEqualTo(king1);
        King king2 = new King("k", "WHITE", Point.valueOf(7, 4));
        assertThat(Pieces.findPiece(7, 4)).isEqualTo(king2);
    }
}
