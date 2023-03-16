package domain.piece;

public abstract class Pawn implements Piece {

    private static final PieceType pieceType = PieceType.PAWN;

    boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate);
}
