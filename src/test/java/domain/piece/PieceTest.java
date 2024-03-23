package domain.piece;

import domain.piece.piecerole.Pawn;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @DisplayName("기물은 기물 종류와 색을 갖는다.")
    @Test
    void generatePiece() {
        Piece pieceType = new Piece(Pawn.from(Color.BLACK), Color.BLACK);
        Assertions.assertThat(pieceType).isEqualTo(new Piece(Pawn.from(Color.BLACK), Color.BLACK));
    }
}
