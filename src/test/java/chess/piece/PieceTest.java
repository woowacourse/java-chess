package chess.piece;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.Path;
import chess.Position;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @DisplayName("말이 검정색인지 확인할 수 있다.")
    @Test
    void test_isBlack() {
        Piece blackPiece = new Piece(Color.BLACK) {
            @Override
            public Path searchPathTo(final Position from, final Position to, final Optional<Piece> destination) {
                return null;
            }
        };
        Piece whitePiece = new Piece(Color.WHITE) {
            @Override
            public Path searchPathTo(final Position from, final Position to, final Optional<Piece> destination) {
                return null;
            }
        };

        assertTrue(blackPiece.isBlack());
        assertFalse(whitePiece.isBlack());
    }
}
