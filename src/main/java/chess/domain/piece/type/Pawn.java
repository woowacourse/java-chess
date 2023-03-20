package chess.domain.piece.type;

import chess.domain.chess.CampType;
import chess.domain.piece.Position;
import chess.domain.piece.move.piece.PawnMove;

public class Pawn extends Piece {

    private final PawnMove pawnMove = new PawnMove();

    public Pawn(final PieceType pieceType, final CampType campType) {
        super(pieceType, campType);
    }

    @Override
    public boolean canMove(final Position source, final Position target) {
        return pawnMove.canMove(source, target);
    }

    @Override
    public boolean canAttack(final Position source, final Position target) {
        return pawnMove.canAttack(source, target);
    }

    @Override
    public boolean isPossibleRoute(final Position source, final Position target, final boolean isPossible) {
        return pawnMove.isPossibleRoute(source, target, isPossible);
    }
}
