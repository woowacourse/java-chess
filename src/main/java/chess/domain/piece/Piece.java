package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public abstract class Piece {

    protected final Symbol symbol;
    protected final Color color;

    protected Piece(final Color color, final Symbol symbol) {
        this.color = color;
        this.symbol = symbol;
    }

    public abstract boolean isMovable(final Position from, final Position to);

    public abstract double getPoint();

    protected boolean checkDirection(final Position from, final Position to, final List<Direction> directions) {
        final Direction direction;

        try {
            direction = Direction.getDirection(from, to);
        } catch(IllegalArgumentException e) {
            return false;
        }

        return directions.contains(direction);
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

    public final Symbol getSymbol() {
        return symbol;
    }

    public final Color getColor() {
        return color;
    }
}
