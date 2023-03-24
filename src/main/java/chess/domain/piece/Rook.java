package chess.domain.piece;

import chess.domain.square.Team;

public class Rook extends Piece {
    public Rook(final Team team, final Role role) {
        super(team, role);
    }

    @Override
    public boolean canMove(final Direction direction, final int distance) {
        return direction.isStraight();
    }

    @Override
    public boolean canAttack(final Direction direction, final int distance, final Piece targetPiece) {
        return canMove(direction, distance) && isOpposite(targetPiece.team);
    }
}
