package chess.domain.piece;

import chess.domain.square.Team;

public class King extends Piece {
    private static final int MAX_MOVE_DISTANCE = 1;

    public King(final Team team, final Role role) {
        super(team, role);
    }

    @Override
    public boolean canMove(final Direction direction, final int distance) {
        return (direction.isDiagonal() || direction.isStraight()) && distance == MAX_MOVE_DISTANCE;
    }

    @Override
    public boolean canAttack(final Direction direction, final int distance, final Piece targetPiece) {
        return canMove(direction, distance) && isOpposite(targetPiece.team);
    }
}
