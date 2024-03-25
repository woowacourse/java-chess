package chess.domain.piece;

import chess.domain.color.Color;
import chess.domain.strategy.MoveStrategy;
import java.util.Map;
import java.util.Set;

public abstract class Piece {
    protected final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public abstract Set<Position> findPath(Position thisPosition, Position destination);

    public abstract MoveStrategy strategy(Map<Position, Piece> board);

    public abstract boolean isBlank();

    public abstract PieceType pieceType();

    public boolean isSameColor(Color otherColor) {
        return color == otherColor;
    }

    public boolean isOppositeColor(Piece other) {
        if (color == Color.WHITE) {
            return other.color == Color.BLACK;
        }
        if (color == Color.BLACK) {
            return other.color == Color.WHITE;
        }
        return false;
    }
}
