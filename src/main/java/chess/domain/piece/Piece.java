package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.strategy.MoveStrategy;

public abstract class Piece {

    protected final MoveStrategy moveStrategy;
    protected final Color color;
    protected final double score;

    public Piece(final MoveStrategy moveStrategy, final Color color, final double score) {
        this.moveStrategy = moveStrategy;
        this.color = color;
        this.score = score;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public abstract boolean isMovable(Position fromPosition, Position toPosition);

    public boolean isKing() {
        return false;
    }

    public boolean isKnight() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isMyTeam(Piece piece) {
        return color == piece.color;
    }

    public boolean isCatchable(Position fromPosition, Position toPosition) {
        return isMovable(fromPosition, toPosition);
    }

    public double getScore() {
        return score;
    }

    public Color getColor() {
        return color;
    }
}