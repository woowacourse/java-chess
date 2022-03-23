package chess.domain.piece;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {

    @Test
    void createPiece() {
        Piece piece = new Queen(Color.WHITE);

        assertThat(piece).isNotNull();
    }
}
