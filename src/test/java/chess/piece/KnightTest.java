package chess.piece;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KnightTest {

    @Test
    void createPiece() {
        Piece piece = new Knight(Color.WHITE);

        assertThat(piece).isNotNull();
    }
}
