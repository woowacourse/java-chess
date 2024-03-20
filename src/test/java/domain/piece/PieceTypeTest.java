package domain.piece;

import domain.piece.piecerole.Pawn;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTypeTest {

    @DisplayName("기물은 기물 종류와 색을 갖는다.")
    @Test
    void generatePiece() {
        PieceType pieceType = new PieceType(new Pawn(), Color.BLACK);
        Assertions.assertThat(pieceType).isEqualTo(new PieceType(new Pawn(), Color.BLACK));
    }
}
