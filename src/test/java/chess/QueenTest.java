package chess;

import chess.piece.Piece;
import chess.piece.Pieces;
import chess.piece.Queen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class QueenTest {
    @DisplayName("Queen 생성")
    @Test
    public void create() {
        Queen queen1 = new Queen("Q", "BLACK", Point.of('d', 8));
        assertThat(Pieces.findPiece('d', 8)).isEqualTo(queen1);
        Queen queen2 = new Queen("q", "WHITE", Point.of('d', 1));
        assertThat(Pieces.findPiece('d', 1)).isEqualTo(queen2);
    }
}
