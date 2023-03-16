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
}
