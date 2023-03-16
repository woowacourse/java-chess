package chess.domain.piece;

import chess.domain.camp.CampType;
import chess.domain.move.QueenMove;

public class Queen extends Piece {

    private final QueenMove queenMove = new QueenMove();

    public Queen(final PieceType pieceType, final CampType campType) {
        super(pieceType, campType);
    }

    @Override
    public boolean canMove(final Position source, final Position target) {
        return queenMove.canMove(source, target);
    }
}
