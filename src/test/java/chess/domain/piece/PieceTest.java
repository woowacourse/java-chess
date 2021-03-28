package chess.domain.piece;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    Piece piece1;
    Piece piece2;

    @BeforeEach
    void setUp() {
        PieceKind pieceKind1 = PieceKind.QUEEN;
        PieceKind pieceKind2 = PieceKind.ROOK;

        PieceColor blackPieceColor = PieceColor.BLACK;
        PieceColor whitePieceColor = PieceColor.WHITE;

        piece1 = new Piece(pieceKind1, blackPieceColor);
        piece2 = new Piece(pieceKind2, whitePieceColor);
    }

    @DisplayName("말 이름 가져오는 기능 테스트")
    @Test
    void getName_PieceName() {
        String pieceSymbol1 = piece1.symbol();
        String pieceSymbol2 = piece2.symbol();

        assertEquals(pieceSymbol1, "Q");
        assertEquals(pieceSymbol2, "r");
    }

    @DisplayName("말 색깔이 같은지 확인하는 기능 테스트")
    @Test
    void isSameColor_boolean() {
        PieceKind pieceKind2 = PieceKind.ROOK;
        PieceColor blackPieceColor = PieceColor.BLACK;

        Piece piece3 = new Piece(pieceKind2, blackPieceColor);

        assertTrue(piece1.isSameColor(piece3));
        assertFalse(piece1.isSameColor(piece2));
    }
}