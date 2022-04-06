package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PiecesTest {

    @Test
    @DisplayName("pawn 반환 확인")
    void findPawnPiece() {
        assertThat(Pieces.findPiece("white", "pawn")).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("queen 반환 확인")
    void findQueenPiece() {
        assertThat(Pieces.findPiece("black", "queen")).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("knight 반환 확인")
    void findKnightPiece() {
        assertThat(Pieces.findPiece("white", "knight")).isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("king 반환 확인")
    void findKingPiece() {
        assertThat(Pieces.findPiece("black", "king")).isInstanceOf(King.class);
    }

    @Test
    @DisplayName("bishop 반환 확인")
    void findBishopPiece() {
        assertThat(Pieces.findPiece("white", "bishop")).isInstanceOf(Bishop.class);
    }

    @Test
    @DisplayName("rook 반환 확인")
    void findRookPiece() {
        assertThat(Pieces.findPiece("white", "rook")).isInstanceOf(Rook.class);
    }
}