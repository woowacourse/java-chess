package domain.piece;

import domain.piecetype.Coordinate;
import domain.piecetype.PieceType;

public class ConcretePiece implements Piece {

    private final PieceType pieceType;
    private final Camp camp;

    public ConcretePiece(PieceType pieceType, Camp camp) {
        this.pieceType = pieceType;
        this.camp = camp;
    }

    @Override
    public Boolean canReap() {
        return pieceType.canReap();
    }

    @Override
    public Boolean isExist() {
        return true;
    }

    @Override
    public Boolean isMovable(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        return pieceType.isReachableByRule(startCoordinate, endCoordinate);
    }

    @Override
    public PieceType getPieceType() {
        return pieceType;
    }

    @Override
    public Camp getCamp() {
        return camp;
    }
}
