package chess.domain.piece;

import chess.domain.piece.Color;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KnightTest {

    @Test
    void createPiece() {
        Piece piece = new Knight(Color.WHITE);

        assertThat(piece).isNotNull();
    }
}
