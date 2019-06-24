package chess.domain;

import chess.domain.moverule.King;
import chess.domain.moverule.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PieceSymbolTest {

    @Test
    public void 동시성_테스트() {
        assertEquals(PieceSymbol.BLACK_KING, PieceSymbol.BLACK_KING);
    }

    @Test
    public void 두개의_Symbol이_다를때() {
        assertFalse(PieceSymbol.BLACK_KING == PieceSymbol.WHITE_KING);
    }

    @Test
    public void getSymbolTest() {
        Piece piece = Piece.of(Piece.Color.WHITE, Pawn.FIRST_BOTTOM);
        String expected = PieceSymbol.getSymbol(piece);
        assertEquals(expected, PieceSymbol.WHITE_PAWN.getSymbol());
    }
}