package chess.domain.piece;

import chess.domain.board.Position;

public class King extends Piece {

    public static final int BOUND = 1;

    public King(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition, Team team) {
        int sourceColumnNumber = sourcePosition.getColumn();
        int targetColumnNumber = targetPosition.getColumn();
        int sourceRankNumber = sourcePosition.getRow();
        int targetRankNumber = targetPosition.getRow();

        return isDifferenceUnderBound(sourceColumnNumber, targetColumnNumber)
                && isDifferenceUnderBound(sourceRankNumber, targetRankNumber)
                && isNotMyPosition(sourcePosition, targetPosition)
                && isNotSameTeam(team);
    }

    private boolean isDifferenceUnderBound(int source, int target) {
        return Math.abs(source - target) <= BOUND;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Piece move() {
        return new King(getTeam());
    }
}
