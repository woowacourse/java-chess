package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Piece {

    protected final Color color;
    protected final double score;

    public Piece(final Color color, final double score) {
        this.color = color;
        this.score = score;
    }

    public boolean isSameColor(final Color color) {
        return this.color == color;
    }

    public abstract boolean isMovable(final Position source, final Position target);

    public boolean isCatchable(Position fromPosition, Position toPosition) {
        return isMovable(fromPosition, toPosition);
    }

    public boolean isKing() {
        return false;
    }

    public boolean isKnight() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isSameTeam(final Piece piece) {
        return color == piece.color;
    }

    public double getScore() {
        return score;
    }

    public Color getColor() {
        return color;
    }
}