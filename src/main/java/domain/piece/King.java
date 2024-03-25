package domain.piece;

import domain.ChessVector;
import domain.Team;
import domain.square.Square;

public class King extends Piece {

    private static final int MOVE_DISTANCE = 1;

    public King(final Team team) {
        super(team, PieceType.KING);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        final ChessVector chessVector = target.calculateVector(source);

        return chessVector.isManhattanDistance(MOVE_DISTANCE);
    }

    @Override
    public boolean canAttack(final Square source, final Square target) {
        return canMove(source, target);
    }
}
