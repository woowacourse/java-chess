package domain.square;

import domain.piece.Coordinate;
import domain.piece.Pawn;
import domain.piece.Piece;

import java.util.Objects;

public final class ConcreteSquare extends Square {

    private final Piece piece;
    private final Camp camp;

    public ConcreteSquare(final Piece piece, final Camp camp) {
        this.piece = piece;
        this.camp = camp;
    }

    @Override
    public Boolean canReap() {
        return piece.canReap();
    }

    @Override
    public Boolean isExist() {
        return true;
    }

    @Override
    public Boolean isMovable(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (super.isFirstMove() && piece.isPawn()) {
            Pawn pawn = (Pawn) piece;
            return pawn.isReachableByRuleWhenFirstMove(startCoordinate, endCoordinate);
        }
        return piece.isReachableByRule(startCoordinate, endCoordinate);
    }

    @Override
    public Piece getPieceType() {
        return piece;
    }

    @Override
    public Camp getCamp() {
        return camp;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConcreteSquare that = (ConcreteSquare) o;
        return piece.getClass() == that.piece.getClass() && camp == that.camp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece, camp);
    }
}
