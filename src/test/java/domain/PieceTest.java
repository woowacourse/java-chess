package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @DisplayName("기물은 기물 종류와 색을 갖는다.")
    @Test
    void generatePiece() {
        Piece piece = new Piece(PieceRole.PAWN, Color.BLACK);
        Assertions.assertThat(piece).isEqualTo(new Piece(PieceRole.PAWN, Color.BLACK));
    }
}
