package chess.piece;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {

    @Test
    void createPiece() {
        Piece piece = new Bishop();

        assertThat(piece).isNotNull();
    }
}
