package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import java.util.Optional;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("isBlack() : 피스가 검정색인지 확인할 수 있다.")
    void test_isBlack() throws Exception {
        //given
        Piece blackPiece = createPiece(Color.BLACK);
        Piece whitePiece = createPiece(Color.WHITE);

        //when & then
        assertTrue(blackPiece.isBlack());
        assertFalse(whitePiece.isBlack());
    }

    @Test
    @DisplayName("isSameColor() : 두 피스가 같은 색인지 구별할 수 있다.")
    void test_isSameColor() throws Exception {
        //given
        final Piece whitePiece = createPiece(Color.WHITE);

        //when & then
        assertTrue(whitePiece.isSameColor(Color.WHITE));
        assertFalse(whitePiece.isSameColor(Color.BLACK));
    }

    private static Piece createPiece(Color color) {
        return new Piece(color) {
            @Override
            public Path searchPathTo(final Position from, final Position to, final Optional<Piece> destination) {
                return null;
            }
        };
    }
}
