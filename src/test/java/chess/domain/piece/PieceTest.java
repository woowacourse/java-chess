package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @DisplayName("체스말 생성 테스트")
    @Test
    void createPiece() {
        Piece piece = new Piece(Color.WHITE, Shape.ROOK);
        Piece kingPiece = new Piece(Color.WHITE, Shape.KING);

        assertThat(piece.isSameColor(Color.WHITE)).isTrue();
        assertThat(piece.isSameColor(Color.BLACK)).isFalse();
        assertThat(piece.isKing()).isFalse();
        assertThat(kingPiece.isKing()).isTrue();
    }

}