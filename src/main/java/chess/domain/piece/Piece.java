package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public abstract class Piece {

    protected final Symbol symbol;
    protected final Color color;

    protected Piece(final Color color, final Symbol symbol) {
        this.color = color;
        this.symbol = symbol;
    }

    public abstract Map<Direction, List<Position>> getMovablePositions(final Position position);

    public abstract double getPoint();

    public abstract Direction getPawnDirection();

    public final String getSymbol() {
        final String value = symbol.getValue();
        if (color == Color.WHITE) {
            return value.toLowerCase(Locale.ROOT);
        }
        return value;
    }

    public final boolean isBlack() {
        return color == Color.BLACK;
    }

    public final boolean isWhite() {
        return color == Color.WHITE;
    }

    public final boolean isEmpty() {
        return color == Color.EMPTY;
    }

    public final boolean isSameColor(final Piece otherPiece) {
        return color == otherPiece.color;
    }

    public final boolean isSameSymbol(final Symbol symbol) {
        return this.symbol == symbol;
    }

    public final boolean isSameColor(final Color color) {
        return this.color == color;
    }

    public final Color getColor() {
        return color;
    }
}
