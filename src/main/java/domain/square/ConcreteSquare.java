package domain.square;

import domain.piece.Coordinate;
import domain.piece.Pawn;
import domain.piece.Piece;

public class ConcreteSquare extends Square {

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
}
