package chess.domain.piece;

import chess.domain.camp.CampType;
import chess.domain.move.QueenMove;

public class Queen extends Piece {

    private final QueenMove queenMove;

    public Queen(final PieceType pieceType, final CampType campType, final QueenMove queenMove) {
        super(pieceType, campType);
        this.queenMove = queenMove;
    }

    @Override
    public boolean canMove(final Position source, final Position target) {
        return queenMove.canMove(source, target);
    }
}
