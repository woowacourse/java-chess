package chess.domain.piece;

import chess.domain.camp.CampType;
import chess.domain.move.KnightMove;

public class Knight extends Piece {

    private final KnightMove knightMove = new KnightMove();

    public Knight(final PieceType pieceType, final CampType campType) {
        super(pieceType, campType);
    }

    @Override
    public boolean canMove(final Position source, final Position target) {
        return knightMove.canMove(source, target);
    }

    @Override
    public boolean canAttack(final Position source, final Position target) {
        return knightMove.canAttack(source, target);
    }

    @Override
    public boolean isPossibleRoute(final Position source, final Position target, final boolean isPossible) {
        return knightMove.isPossibleRoute(source, target, isPossible);
    }
}
