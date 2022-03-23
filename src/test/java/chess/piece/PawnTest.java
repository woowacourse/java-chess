package chess.piece;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @Test
    void createPiece() {
        Piece piece = new Pawn(Color.WHITE);

        assertThat(piece).isNotNull();
    }
}
