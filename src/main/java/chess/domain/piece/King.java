package chess.domain.piece;

import chess.domain.board.Position;

public class King extends Piece {

    private static final int BOUND = 1;

    public King(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition, Team team) {
        return isDifferenceUnderBound(sourcePosition, targetPosition)
                && isNotMyPosition(sourcePosition, targetPosition)
                && isNotSameTeam(team);
    }

    public boolean isDifferenceUnderBound(Position sourcePosition, Position targetPosition) {
        return sourcePosition.calculateColumnDifferenceWith(targetPosition) <= BOUND
                && sourcePosition.calculateRowDifferenceWith(targetPosition) <= BOUND;
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
