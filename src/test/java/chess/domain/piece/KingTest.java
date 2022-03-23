package chess.domain.piece;

import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {

    @Test
    void createPiece() {
        Piece piece = new King(Color.WHITE);

        assertThat(piece).isNotNull();
    }
}
