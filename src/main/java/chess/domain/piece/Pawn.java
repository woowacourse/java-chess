package chess.domain.piece;

import chess.domain.camp.CampType;
import chess.domain.move.PawnMove;

public class Pawn extends Piece {

    private final PawnMove pawnMove;

    public Pawn(final PieceType pieceType, final CampType campType, final PawnMove pawnMove) {
        super(pieceType, campType);
        this.pawnMove = pawnMove;
    }

    @Override
    public boolean canMove(final Position source, final Position target) {
        return pawnMove.canMove(source, target);
    }
}
