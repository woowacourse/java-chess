package chess.domain.piece;

import chess.domain.Board;
import chess.domain.position.Position;

import java.util.Locale;

public abstract class Piece {

    private final String name;
    protected final Color color;

    protected Piece(final Color color, final String name) {
        this.color = color;
        this.name = name;
    }

    public final String getName() {
        if (color.equals(Color.BLACK)) {
            return name.toUpperCase(Locale.ROOT);
        }
        return name;
    }

    public boolean isSameColor(final Color other) {
        return color == other;
    }

    public void checkPieceMoveRange(final Board board, final Position from, final Position to){

    }
}
