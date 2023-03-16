package chess.domain.piece;

import chess.domain.camp.CampType;
import chess.domain.move.KnightMove;

public class Knight extends Piece {

    private final KnightMove knightMove;

    public Knight(final PieceType pieceType, final CampType campType, final KnightMove knightMove) {
        super(pieceType, campType);
        this.knightMove = knightMove;
    }

    @Override
    public boolean canMove(final Position source, final Position target) {
        return knightMove.canMove(source, target);
    }
}
