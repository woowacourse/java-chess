package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @Test
    @DisplayName("폰을 만든다.")
    void createPiece() {
        Piece piece = new Pawn(Color.WHITE);

        assertThat(piece).isNotNull();
    }
}
