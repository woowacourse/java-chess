package chess.domain.piece;

import chess.domain.board.Position;

public class Knight extends Piece {

    private static final int TWO_DIFFERENCE = 2;
    private static final int ONE_DIFFERENCE = 1;

    public Knight(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition, Team team) {
        if (sourcePosition.calculateColumnDifferenceWith(targetPosition) == TWO_DIFFERENCE) {
            return sourcePosition.calculateRowDifferenceWith(targetPosition) == ONE_DIFFERENCE && isNotSameTeam(team);
        }
        if (sourcePosition.calculateColumnDifferenceWith(targetPosition) == ONE_DIFFERENCE) {
            return sourcePosition.calculateRowDifferenceWith(targetPosition) == TWO_DIFFERENCE && isNotSameTeam(team);
        }
        return false;
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
