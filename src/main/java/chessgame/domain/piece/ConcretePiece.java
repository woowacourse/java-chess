package chessgame.domain.piece;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piecetype.Pawn;
import chessgame.domain.piecetype.PieceType;
import chessgame.domain.piecetype.PieceTypeSymbol;

import java.util.Optional;

public class ConcretePiece extends Piece {

    private final PieceType pieceType;
    private final Camp camp;

    public ConcretePiece(final PieceType pieceType, final Camp camp) {
        this.pieceType = pieceType;
        this.camp = camp;
    }

    @Override
    public boolean canReap() {
        return pieceType.canReap();
    }

    @Override
    public boolean isExist() {
        return true;
    }

    @Override
    public boolean isMovable(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (super.isFirstMove() && pieceType.isSameTypeWith(PieceTypeSymbol.PAWN)) {
            Pawn pawn = (Pawn) pieceType;
            return pawn.isReachableByRuleWhenFirstMove(startCoordinate, endCoordinate);
        }
        return pieceType.isReachableByRule(startCoordinate, endCoordinate);
    }

    @Override
    public boolean isCatchable(final Optional<Camp> otherCamp,
                               final Coordinate startCoordinate,
                               final Coordinate endCoordinate) {
        if (otherCamp.isPresent() && isSameCamp(otherCamp.get())) {
            return false;
        }
        if (pieceType.isSameTypeWith(PieceTypeSymbol.PAWN)) {
            Pawn pawn = (Pawn) pieceType;
            return pawn.isReachableWhenCatch(startCoordinate, endCoordinate);
        }
        return pieceType.isReachableByRule(startCoordinate, endCoordinate);
    }

    @Override
    public Optional<PieceType> getPiece() {
        return Optional.of(pieceType);
    }

    @Override
    public Optional<Camp> camp() {
        return Optional.of(camp);
    }

    @Override
    public boolean isSameCamp(final Camp camp) {
        return this.camp.equals(camp);
    }
}
