package chess.piece;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {

    @Test
    void createPiece() {
        Piece piece = new King();

        assertThat(piece).isNotNull();
    }
}
