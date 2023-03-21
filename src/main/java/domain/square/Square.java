package domain.square;

import domain.piece.move.Coordinate;
import domain.piece.EmptyPiece;
import domain.piece.pawn.Pawn;
import domain.piece.Piece;

import java.util.Objects;

public final class Square implements Cloneable {

    private final Piece piece;
    private final Camp camp;
    private boolean isFirstMove;

    public Square(final Piece piece, final Camp camp) {
        this.piece = piece;
        this.camp = camp;
        this.isFirstMove = true;
    }

    public static Square ofEmpty() {
        return new Square(new EmptyPiece(), Camp.NEUTRAL);
    }

    public boolean isMovable(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (isFirstMove && piece.isPawn()) {
            Pawn pawn = (Pawn) piece;
            return pawn.isReachableByRuleWhenFirstMove(startCoordinate, endCoordinate);
        }
        return piece.isReachableByRule(startCoordinate, endCoordinate);
    }

    public boolean canReap() {
        return piece.canReap();
    }

    public boolean isNotSameCampWith(final Square other) {
        return this.camp == other.getCamp();
    }

    public boolean isKing() {
        return piece.isKing();
    }

    public void checkMoved() {
        isFirstMove = false;
    }

    public Piece getPieceType() {
        return piece;
    }

    public Camp getCamp() {
        return camp;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square that = (Square) o;
        return piece.getClass() == that.piece.getClass() &&
                camp == that.camp &&
                isFirstMove == that.isFirstMove;
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece, camp);
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
