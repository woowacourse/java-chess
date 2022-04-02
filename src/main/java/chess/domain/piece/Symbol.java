package chess.domain.piece;

import static chess.domain.piece.Team.*;

public enum Symbol {
    KING("k"),
    QUEEN("q"),
    BISHOP("b"),
    KNIGHT("n"),
    ROOK("r"),
    PAWN("p");

    private final String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol(Team team) {
        if (team.equals(BLACK)) {
            return symbol.toUpperCase();
        }
        return symbol;
    }
}
