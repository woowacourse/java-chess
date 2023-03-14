package chess.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {

    @DisplayName("색을 구별할 수 있다")
    @Test
    void isSameColor() {
        Piece piece = new Piece(true);
        Piece otherPiece = new Piece(false);

        Assertions.assertThat(piece.hasSameColor(otherPiece)).isFalse();
    }
}
