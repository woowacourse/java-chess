package domain.square;

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

    public boolean isPieceMovable(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        return piece.isReachableByRule(startCoordinate, endCoordinate);
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

    public Piece getPiece() {
        return piece;
    }

    public Color getColor() {
        return color;
    }
}
