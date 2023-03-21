package domain.square;

import domain.piece.move.Coordinate;
import domain.piece.EmptyPiece;
import domain.piece.Piece;

import java.util.Objects;

public final class Square implements Cloneable {

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

    public void checkPieceMoved() {
        piece.checkMoved();
    }

    public Piece getPiece() {
        return piece;
    }

    public Color getColor() {
        return color;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square that = (Square) o;
        return piece.getClass() == that.piece.getClass() &&
                color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece, color);
    }

    @Override
    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
