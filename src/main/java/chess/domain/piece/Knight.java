package chess.domain.piece;

import chess.domain.board.Position;

public class Knight extends Piece {

    public Knight(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition, Team team) {
        if (sourcePosition.calculateColumnDifferenceWith(targetPosition) == 2) {
            return sourcePosition.calculateRowDifferenceWith(targetPosition) == 1 && isNotSameTeam(team);
        }
        if (sourcePosition.calculateColumnDifferenceWith(targetPosition) == 1) {
            return sourcePosition.calculateRowDifferenceWith(targetPosition) == 2 && isNotSameTeam(team);
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
