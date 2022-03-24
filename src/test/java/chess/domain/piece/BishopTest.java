package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {

    @Test
    @DisplayName("비숍을 만든다.")
    void createPiece() {
        Piece piece = new Bishop(Color.WHITE);

        assertThat(piece).isNotNull();
    }
}
