package domain.piece;

import domain.board.position.Vector;

public abstract class Piece {
    private final Color color;

    private final double score;

    protected Piece(final Color color) {
        this.color = color;
        this.score = 0;
    }

    protected Piece(final Color color, final double score) {
        this.color = color;
        this.score = score;
    }

    public Piece move() {
        return this;
    }

    public boolean isReachable(final Vector vector, final Piece targetPiece) {
        if (this.isSameColorWith(targetPiece)) {
            return false;
        }

        return isInstanceReachable(vector, targetPiece);
    }

    public boolean isSameColorWith(final Piece piece) {
        return this.color.isSameColor(piece.color);
    }

    protected abstract boolean isInstanceReachable(final Vector vector, final Piece targetPiece);

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public boolean hasColor(final Color color) {
        return this.color == color;
    }

    public Color getColor() {
        return color;
    }

    public double getScore() {
        return score;
    }
    public String getName(){
        return getClass().getSimpleName();
    }
}
