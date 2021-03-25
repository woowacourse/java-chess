package chess.domain.piece;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    Piece blackQueen;
    Piece whiteRook;

    @BeforeEach
    void setUp() {
        PieceKind pieceKind1 = PieceKind.QUEEN;
        PieceKind pieceKind2 = PieceKind.ROOK;

        PieceColor blackPieceColor = PieceColor.BLACK;
        PieceColor whitePieceColor = PieceColor.WHITE;

        blackQueen = new Piece(pieceKind1, blackPieceColor);
        whiteRook = new Piece(pieceKind2, whitePieceColor);
    }

    @DisplayName("말 이름 가져오는 기능 테스트")
    @Test
    void getName_PieceName() {
        String pieceSymbol1 = blackQueen.symbol();
        String pieceSymbol2 = whiteRook.symbol();

        assertEquals(pieceSymbol1, "Q");
        assertEquals(pieceSymbol2, "r");
    }

    @DisplayName("말 색깔이 같은지 확인하는 기능 테스트")
    @Test
    void isSameColor_boolean() {
        PieceKind pieceKind2 = PieceKind.ROOK;
        PieceColor blackPieceColor = PieceColor.BLACK;

        Piece piece3 = new Piece(pieceKind2, blackPieceColor);

        assertTrue(blackQueen.isSameColor(piece3));
        assertFalse(blackQueen.isSameColor(whiteRook));
    }
}