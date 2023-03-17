package chessgame.domain.piece;

public abstract class Pawn implements Piece {

    private static final PieceType pieceType = PieceType.PAWN;

    public abstract boolean isReachableByRuleWhenFirstMove(final Coordinate startCoordinate, final Coordinate endCoordinate);
    public abstract boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate);

    @Override
    public boolean isSameTypeWith(final PieceType otherType) {
        return pieceType == otherType;
    }
}
