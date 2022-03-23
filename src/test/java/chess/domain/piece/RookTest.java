package chess.domain.piece;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {

    @Test
    void createPiece() {
        Piece piece = new Rook(Color.WHITE);

        assertThat(piece).isNotNull();
    }
}
