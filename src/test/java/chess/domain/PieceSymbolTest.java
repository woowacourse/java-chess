package chess.domain;

import chess.domain.rule.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PieceSymbolTest {
    @Test
    void Symbol_리턴_테스트() {
        Rule rule = Rook.getInstance();
        Piece.Color color = Piece.Color.WHITE;

        String actual = PieceSymbol.WHITE_ROOK.getSymbol();

        String expected = PieceSymbol.getSymbol(color, rule);

        assertEquals(expected, actual);
    }

    @Test
    void Symbol_EMPTY_리턴_테스트() {
        Rule rule = Rook.getInstance();
        Piece.Color color = Piece.Color.EMPTY;

        String actual = PieceSymbol.EMPTY_SYMBOL;

        String expected = PieceSymbol.getSymbol(color, rule);

        assertEquals(expected, actual);
    }
}