package chess;

public class Pawn extends Piece {

    public Pawn(final Team team) {
        super(team);
    }

    @Override
    boolean isMovable(final Position startPosition, final Position endPosition) {
        int diffFile = endPosition.calculateFileDistance(startPosition);
        int diffRank = endPosition.calculateRankDistance(startPosition);
        if (startPosition.equals(endPosition) || diffRank != 0) {
            return false;
        }
        if (team == Team.BLACK) {
            return diffFile == -1 || (!trace.hasLog() && diffFile == -2);
        }
        return diffFile == 1 || (!trace.hasLog() && diffFile == 2);
    }
}

