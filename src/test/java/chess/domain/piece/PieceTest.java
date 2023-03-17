package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Path;
import chess.domain.position.Position;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("isBlack() : 피스가 검정색인지 확인할 수 있다.")
    void test_isBlack() throws Exception {
        //given
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

        //when & then
        assertTrue(blackPiece.isBlack());
        assertFalse(whitePiece.isBlack());
    }
}
