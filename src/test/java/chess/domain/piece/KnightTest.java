package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KnightTest {

    @Test
    @DisplayName("나이트를 만든다.")
    void createPiece() {
        Piece piece = new Knight(Color.WHITE);

        assertThat(piece).isNotNull();
    }
}
