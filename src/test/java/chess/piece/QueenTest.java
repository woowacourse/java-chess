package chess.piece;

import chess.domain.Point;
import chess.domain.piece.Pieces;
import chess.domain.piece.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {
    @DisplayName("Queen 생성")
    @Test
    public void create() {
        Queen queen1 = new Queen("Q", "BLACK", Point.valueOf(0, 3));
        assertThat(Pieces.findPiece(0, 3)).isEqualTo(queen1);
        Queen queen2 = new Queen("q", "WHITE", Point.valueOf(7, 3));
        assertThat(Pieces.findPiece(7, 3)).isEqualTo(queen2);
    }
}
