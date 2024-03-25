package domain.piece;

import domain.ChessVector;
import domain.Team;
import domain.square.Square;

public class Bishop extends Piece {

    public Bishop(final Team team) {
        super(team, PieceType.BISHOP);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        final ChessVector chessVector = target.calculateVector(source);

        return chessVector.isDiagonal();
    }

    @Override
    public boolean canAttack(final Square source, final Square target) {
        return canMove(source, target);
    }
}
