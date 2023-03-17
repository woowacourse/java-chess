package chess.domain.piece;

import chess.domain.board.Position;

public class King extends Piece {

    public King(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition, Team team) {
        int sourceColumnNumber = sourcePosition.getColumn();
        int targetColumnNumber = targetPosition.getColumn();
        int sourceRankNumber = sourcePosition.getRow();
        int targetRankNumber = targetPosition.getRow();

        return isDifferenceUnder(sourceColumnNumber, targetColumnNumber, 1)
                && isDifferenceUnder(sourceRankNumber, targetRankNumber, 1)
                && isNotMyPosition(sourcePosition, targetPosition)
                && isNotSameTeam(team);
    }

    private boolean isDifferenceUnder(int source, int target, int bound) {
        return Math.abs(source - target) <= bound;
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
