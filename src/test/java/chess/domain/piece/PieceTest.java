package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.position.Path;
import chess.domain.position.Position;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("isBlack() : 피스가 검정색인지 확인할 수 있다.")
    void test_isBlack() {
        final Piece blackPiece = new TestPiece(Color.BLACK);
        final Piece whitePiece = new TestPiece(Color.WHITE);

        assertAll(
                () -> assertTrue(blackPiece.isBlack()),
                () -> assertFalse(whitePiece.isBlack())
        );
    }

    static class TestPiece extends Piece {

        public TestPiece(final Color color) {
            super(color);
        }

        @Override
        public Path searchPathTo(final Position from, final Position to, final Optional<Piece> destination) {
            throw new UnsupportedOperationException();
        }
    }
}
