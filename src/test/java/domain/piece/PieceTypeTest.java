package domain.piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTypeTest {

    @DisplayName("기물은 기물 종류와 색을 갖는다.")
    @Test
    void generatePiece() {
        PieceType pieceType = new PieceType(PieceRole.PAWN, Color.BLACK);
        Assertions.assertThat(pieceType).isEqualTo(new PieceType(PieceRole.PAWN, Color.BLACK));
    }
}
