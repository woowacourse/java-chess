package chess.domain.piece.type;

import chess.domain.chess.CampType;
import chess.domain.piece.Position;
import chess.domain.piece.move.piece.QueenMove;

public class Queen extends Piece {

    private final QueenMove queenMove = new QueenMove();

    public Queen(final PieceType pieceType, final CampType campType) {
        super(pieceType, campType);
    }

    @Override
    public boolean canMove(final Position source, final Position target) {
        return queenMove.canMove(source, target);
    }

    @Override
    public boolean canAttack(final Position source, final Position target) {
        return queenMove.canAttack(source, target);
    }

    @Override
    public boolean isPossibleRoute(final Position source, final Position target, final boolean isPossible) {
        return queenMove.isPossibleRoute(source, target, isPossible);
    }
}
