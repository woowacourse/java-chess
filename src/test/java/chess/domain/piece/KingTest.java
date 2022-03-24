package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {

    @Test
    @DisplayName("킹을 만든다.")
    void createPiece() {
        Piece piece = new King(Color.WHITE);

        assertThat(piece).isNotNull();
    }
}
