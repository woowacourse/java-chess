package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @DisplayName("체스말 생성 테스트")
    @Test
    void createPiece() {
        Piece piece = new Piece(Color.WHITE, Shape.ROOK, new Position(0, 0), null);
        Piece kingPiece = new Piece(Color.WHITE, Shape.KING, new Position(0, 0), null);

        assertThat(piece.isSameColor(Color.WHITE)).isTrue();
        assertThat(piece.isSameColor(Color.BLACK)).isFalse();
        assertThat(piece.isKing()).isFalse();
        assertThat(kingPiece.isKing()).isTrue();
    }

    @DisplayName("색상에 따라 노테이션을 반환하는 테스트")
    @Test
    void getNotation() {
        Piece whitePiece = new Piece(Color.WHITE, Shape.ROOK, new Position(0, 0), null);
        Piece blackPiece = new Piece(Color.BLACK, Shape.ROOK, new Position(0, 0), null);

        assertThat(whitePiece.getNotation()).isEqualTo("r");
        assertThat(blackPiece.getNotation()).isEqualTo("R");
    }
}