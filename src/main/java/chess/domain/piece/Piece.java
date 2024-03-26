package chess.domain.piece;

import chess.domain.color.Color;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.strategy.MoveStrategy;
import java.util.Map;
import java.util.Set;

public abstract class Piece {
    protected final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public abstract Set<Position> findPath(Positions positions);

    public abstract MoveStrategy strategy(Map<Position, Piece> board);

    public abstract boolean isBlank();

    public abstract boolean isPawn();

    public abstract PieceType pieceType();

    public final boolean isSameColor(Color otherColor) {
        return color == otherColor;
    }

    public final boolean isOppositeColor(Piece other) {
        if (color == Color.WHITE) {
            return other.color == Color.BLACK;
        }
        if (color == Color.BLACK) {
            return other.color == Color.WHITE;
        }
        return false;
    }
}
