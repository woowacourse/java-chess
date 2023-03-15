package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PiecesTest {

    @Test
    @DisplayName("생성한 pieces의 개수는 16개이다.")
    void checkPieceSize() {
        Pieces pieces = PiecesFactory.create(BLACK);
        assertThat(pieces.getPieces()).hasSize(16);
    }
}
