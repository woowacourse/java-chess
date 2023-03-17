package chess.domain.piece;

import chess.domain.camp.CampType;
import chess.domain.move.BishopMove;

public class Bishop extends Piece {

    private final BishopMove bishopMove = new BishopMove();

    public Bishop(final PieceType pieceType, final CampType campType) {
        super(pieceType, campType);
    }

    @Override
    public boolean canMove(final Position source, final Position target) {
        return bishopMove.canMove(source, target);
    }

    @Override
    public boolean canAttack(final Position source, final Position target) {
        return bishopMove.canAttack(source, target);
    }

    @Override
    public boolean isPossibleRoute(final Position source, final Position target, final boolean isPossible) {
        return bishopMove.isPossibleRoute(source, target, isPossible);
    }
}
