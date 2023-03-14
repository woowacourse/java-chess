package chess;

public class Rook implements PieceType {

    @Override
    public boolean isMovable(final Position startPosition, final Position endPosition) {
        if (startPosition.equals(endPosition)) {
            return false;
        }
        int diffFile = endPosition.calculateFileDistance(startPosition);
        int diffRank = endPosition.calculateRankDistance(startPosition);

        return (diffFile * diffRank == 0);
    }
}
