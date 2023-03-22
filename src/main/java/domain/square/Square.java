package domain.square;

import domain.piece.move.Situation;
import domain.piece.move.Coordinate;
import domain.piece.EmptyPiece;
import domain.piece.Piece;

public final class Square {

    private final Piece piece;
    private final Color color;

    public Square(final Piece piece, final Color color) {
        this.piece = piece;
        this.color = color;
    }

    public static Square ofEmptyPiece() {
        return new Square(new EmptyPiece(), Color.NEUTRAL);
    }

    public boolean isPieceMovable(
            final Coordinate start,
            final Coordinate end,
            final Situation situation
    ) {
        return piece.isReachableByRule(start, end, situation);
    }

    public boolean hasPieceCanJump() {
        return piece.canJump();
    }

    public boolean hasPieceNotSameColorWith(final Square other) {
        return this.color == other.getColor();
    }

    public boolean hasKing() {
        return piece.isKing();
    }

    public boolean hasPawn() {
        return piece.isPawn();
    }

    public double getPoint() {
        return piece.getPoint();
    }

    public Piece getPiece() {
        return piece;
    }

    public Color getColor() {
        return color;
    }
}
