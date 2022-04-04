package chess.domain.piece;

import chess.domain.Position;

public abstract class Piece {

    protected final Color color;
    protected final double score;

    public Piece(final Color color, final double score) {
        this.color = color;
        this.score = score;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public abstract boolean isMovable(Position fromPosition, Position toPosition);

    public boolean isKing() {
        return this instanceof King;
    }

    public boolean isPawn() {
        return this instanceof Pawn;
    }

    public boolean isMyTeam(Piece piece) {
        return color == piece.color;
    }

    public abstract boolean isCatchable(Position fromPosition, Position toPosition);

    public double getScore() {
        return score;
    }

    public Color getColor() {
        return color;
    }

    public String getPiece() {
        return color.toString() + "_" + this.getClass().getSimpleName();
    }
}
