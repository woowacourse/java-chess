package chess.domain.piece;

import chess.domain.camp.CampType;
import chess.domain.move.KingMove;

public class King extends Piece {

    private final KingMove kingMove;

    public King(final PieceType pieceType, final CampType campType, final KingMove kingMove) {
        super(pieceType, campType);
        this.kingMove = kingMove;
    }

    @Override
    public boolean canMove(final Position source, final Position target) {
        return kingMove.canMove(source, target);
    }
}
