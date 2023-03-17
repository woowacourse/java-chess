package chess.domain.piece;

import chess.domain.board.Position;

public class Knight extends Piece {

    public Knight(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition, Team team) {
        int sourceColumnNumber = sourcePosition.getColumn();
        int targetColumnNumber = targetPosition.getColumn();
        int sourceRankNumber = sourcePosition.getRow();
        int targetRankNumber = targetPosition.getRow();

        if (isDifferenceWith(sourceColumnNumber, targetColumnNumber, 2)) {
            return isDifferenceWith(sourceRankNumber, targetRankNumber, 1) && isNotSameTeam(team);
        }
        if (isDifferenceWith(sourceColumnNumber, targetColumnNumber, 1)) {
            return isDifferenceWith(sourceRankNumber, targetRankNumber, 2) && isNotSameTeam(team);
        }
        return false;
    }

    private boolean isDifferenceWith(int sourceColumnNumber, int targetColumnNumber, int x) {
        return Math.abs(sourceColumnNumber - targetColumnNumber) == x;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Piece move() {
        return new Knight(getTeam());
    }
}
