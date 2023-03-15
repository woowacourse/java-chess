package chess;

public class Bishop extends Piece {
    public Bishop(Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(final Position startPosition, final Position endPosition) {
        if (startPosition.equals(endPosition)) {
            return false;
        }
        int diffFile = endPosition.calculateFileDistance(startPosition);
        int diffRank = endPosition.calculateRankDistance(startPosition);

        return (Math.abs(diffFile) == Math.abs(diffRank));
    }
}
