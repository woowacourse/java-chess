package chessgame.domain.square;

import chessgame.domain.piece.Coordinate;
import chessgame.domain.piece.Pawn;
import chessgame.domain.piece.Piece;
import chessgame.domain.piece.PieceType;

import java.util.Optional;

public class ConcreteSquare extends Square {

    private final Piece piece;
    private final Camp camp;

    public ConcreteSquare(Piece piece, Camp camp) {
        this.piece = piece;
        this.camp = camp;
    }

    @Override
    public boolean canReap() {
        return piece.canReap();
    }

    @Override
    public boolean isExist() {
        return true;
    }

    @Override
    public boolean isMovable(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (super.isFirstMove() && piece.isSameTypeWith(PieceType.PAWN)) {
            Pawn pawn = (Pawn) piece;
            return pawn.isReachableByRuleWhenFirstMove(startCoordinate, endCoordinate);
        }
        return piece.isReachableByRule(startCoordinate, endCoordinate);
    }

    @Override
    public Optional<Piece> getPieceType() {
        return Optional.of(piece);
    }

    @Override
    public boolean isSameCamp(Camp camp) {
        return this.camp.equals(camp);
    }
}
