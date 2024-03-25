package domain.piece;

import domain.ChessVector;
import domain.Team;
import domain.square.Square;

public class Queen extends Piece {

    public Queen(final Team team) {
        super(team, PieceType.QUEEN);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        final ChessVector chessVector = target.calculateVector(source);

        return chessVector.isDiagonal() || chessVector.isHorizontalOrVertical();
    }

    @Override
    public boolean canAttack(final Square source, final Square target) {
        return canMove(source, target);
    }
}
