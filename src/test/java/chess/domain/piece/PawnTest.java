package chess.domain.piece;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @Test
    void createPiece() {
        Piece piece = new Pawn(Color.WHITE);

        assertThat(piece).isNotNull();
    }
}
