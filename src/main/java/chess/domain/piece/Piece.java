package chess.domain.piece;

import chess.domain.Color;
import java.util.Locale;

public abstract class Piece {

    protected final PieceName name;
    protected final Color color;

    protected Piece(Color color, PieceName name) {
        this.color = color;
        this.name = name;
    }

    public String getName() {
        String symbol = name.getSymbol();
        if (color == Color.WHITE) {
            return symbol.toLowerCase(Locale.ROOT);
        }
        return symbol;
    }
}
