package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Piece {
    private final Color color;
    private final PieceType type;

    protected Piece(final Color color, final PieceType type) {
        this.color = color;
        this.type = type;
    }

    public boolean isNotMovable(Position sourcePosition, Position targetPosition, Piece target) {
        return !isMovable(sourcePosition, targetPosition, target);
    }

    public boolean isMovable(final Position start, final Position end, final Piece target) {
        return isValidMove(start, end) && isValidTarget(target);
    }

    protected abstract boolean isValidMove(final Position start, final Position end);

    protected abstract boolean isValidTarget(final Piece target);

    public PieceType type() {
        return type;
    }

    public Color color() {
        return color;
    }
}
