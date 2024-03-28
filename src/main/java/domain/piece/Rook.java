package domain.piece;

import domain.ChessVector;
import domain.Team;
import domain.square.Square;

public class Rook extends Piece {

    public Rook(final Team team) {
        super(team, PieceType.ROOK);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        final ChessVector chessVector = target.calculateVector(source);

        return chessVector.isHorizontalOrVertical();
    }

    @Override
    public boolean canAttack(final Square source, final Square target) {
        return canMove(source, target);
    }
}
