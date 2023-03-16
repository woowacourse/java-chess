package chess.domain.piece;

import chess.domain.camp.CampType;
import chess.domain.move.BishopMove;

public class Bishop extends Piece {

    private final BishopMove bishopMove;

    public Bishop(final PieceType pieceType, final CampType campType, final BishopMove bishopMove) {
        super(pieceType, campType);
        this.bishopMove = bishopMove;
    }

    @Override
    public boolean canMove(final Position source, final Position target) {
        return bishopMove.canMove(source, target);
    }
}
