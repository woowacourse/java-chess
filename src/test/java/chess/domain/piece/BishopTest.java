package chess.domain.piece;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {

    @Test
    void createPiece() {
        Piece piece = new Bishop(Color.WHITE);

        assertThat(piece).isNotNull();
    }
}
