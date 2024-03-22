package chess.domain.piece;

import chess.domain.color.Color;
import chess.domain.strategy.MoveStrategy;
import java.util.Map;
import java.util.Set;

public abstract class Piece {
    protected final Position position;
    protected final Color color;

    protected Piece(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public abstract Set<Position> findPathTo(Position destination);

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

    public abstract Piece update(Position destination);

    public abstract MoveStrategy strategy(Map<Position, Piece> board);

    public abstract PieceType pieceType();
}
