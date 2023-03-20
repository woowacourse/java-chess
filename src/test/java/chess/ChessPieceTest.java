package chess;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.piece.ChessPiece;
import chess.piece.Shape;
import chess.piece.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessPieceTest {

    @Test
    @DisplayName("각 기물들이 올바르게 생성된다.")
    void shouldSuccessGeneratePieces() {
        assertSoftly(softly -> {
            assertDoesNotThrow(() -> new ChessPiece(Shape.KING, Side.BLACK));
            assertDoesNotThrow(() -> new ChessPiece(Shape.KING, Side.BLACK));
            assertDoesNotThrow(() -> new ChessPiece(Shape.KING, Side.WHITE));
            assertDoesNotThrow(() -> new ChessPiece(Shape.QUEEN, Side.BLACK));
            assertDoesNotThrow(() -> new ChessPiece(Shape.QUEEN, Side.WHITE));
            assertDoesNotThrow(() -> new ChessPiece(Shape.KNIGHT, Side.BLACK));
            assertDoesNotThrow(() -> new ChessPiece(Shape.KNIGHT, Side.WHITE));
            assertDoesNotThrow(() -> new ChessPiece(Shape.BISHOP, Side.BLACK));
            assertDoesNotThrow(() -> new ChessPiece(Shape.BISHOP, Side.WHITE));
            assertDoesNotThrow(() -> new ChessPiece(Shape.ROOK, Side.BLACK));
            assertDoesNotThrow(() -> new ChessPiece(Shape.ROOK, Side.WHITE));
            assertDoesNotThrow(() -> new ChessPiece(Shape.PAWN, Side.BLACK));
            assertDoesNotThrow(() -> new ChessPiece(Shape.PAWN, Side.WHITE));
        });
    }
}
