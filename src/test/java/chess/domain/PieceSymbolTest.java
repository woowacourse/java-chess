package chess.domain;

import chess.domain.rule.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PieceSymbolTest {
    @Test
    void Symbol_리턴_테스트() {
        Rule rule = Rook.getInstance();
        Piece.Color color = Piece.Color.WHITE;
        Position position = Position.of("1", "a");

        String actual = PieceSymbol.WHITE_ROOK.getSymbol();

        Piece piece = Piece.of(position, color, rule);
        String expected = PieceSymbol.getSymbol(piece);

        assertEquals(expected, actual);
    }

    @Test
    void Symbol_EMPTY_리턴_테스트() {
        Rule rule = Rook.getInstance();
        Piece.Color color = Piece.Color.EMPTY;
        Position position = Position.of("1", "a");

        String actual = PieceSymbol.EMPTY_SYMBOL;

        Piece piece = Piece.of(position, color, rule);
        String expected = PieceSymbol.getSymbol(piece);

        assertEquals(expected, actual);
    }
}