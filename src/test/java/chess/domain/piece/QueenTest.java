package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {

    @Test
    @DisplayName("퀸을 만든다.")
    void createPiece() {
        Piece piece = new Queen(Color.WHITE);

        assertThat(piece).isNotNull();
    }
}
