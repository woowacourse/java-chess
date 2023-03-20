package chess.domain.piece.type;

import chess.domain.chess.CampType;
import chess.domain.piece.Position;
import chess.domain.piece.move.piece.RookMove;

public class Rook extends Piece {

    private final RookMove rookMove = new RookMove();

    public Rook(final PieceType pieceType, final CampType campType) {
        super(pieceType, campType);
    }

    @Override
    public boolean canMove(final Position source, final Position target) {
        return rookMove.canMove(source, target);
    }

    @Override
    public boolean canAttack(final Position source, final Position target) {
        return rookMove.canAttack(source, target);
    }

    @Override
    public boolean isPossibleRoute(final Position source, final Position target, final boolean isPossible) {
        return rookMove.isPossibleRoute(source, target, isPossible);
    }
}
