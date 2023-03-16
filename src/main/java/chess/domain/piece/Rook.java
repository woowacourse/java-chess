package chess.domain.piece;

import chess.domain.camp.CampType;
import chess.domain.move.RookMove;

public class Rook extends Piece {

    private final RookMove rookMove = new RookMove();

    public Rook(final PieceType pieceType, final CampType campType) {
        super(pieceType, campType);
    }

    @Override
    public boolean canMove(final Position source, final Position target) {
        return rookMove.canMove(source, target);
    }
}
