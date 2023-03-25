package chessgame.domain.piece;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.chessgame.Camp;

public abstract class Piece {

    private final PieceType pieceType;
    private final Camp camp;
    private final double score;

    protected Piece(final PieceType pieceType, final Camp camp, final double score) {
        this.pieceType = pieceType;
        this.camp = camp;
        this.score = score;
    }

    public abstract boolean isReachableByRule(final Coordinate startCoordinate,
                                              final Coordinate endCoordinate);

    public abstract boolean isCatchable(final Camp otherCamp,
                                        final Coordinate startCoordinate,
                                        final Coordinate endCoordinate);


    public abstract boolean canReap();

    public boolean isEmpty() {
        return pieceType.isEmpty();
    }

    public boolean isSameCamp(final Camp camp) {
        return this.camp.equals(camp);
    }

    public boolean isSameTypeWith(final PieceType otherType) {
        return this.pieceType.equals(otherType);
    }

    public Camp camp() {
        return camp;
    }

    public double score() {
        return score;
    }
}
