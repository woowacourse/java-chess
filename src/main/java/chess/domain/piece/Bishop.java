package chess.domain.piece;

import chess.domain.camp.TeamColor;
import chess.domain.move.BishopMove;

public class Bishop extends Piece {

    private final BishopMove bishopMove = new BishopMove();

    public Bishop(final PieceType pieceType, final TeamColor teamColor) {
        super(pieceType, teamColor);
    }

    @Override
    public boolean canMove(final Position source, final Position target) {
        return bishopMove.canMove(source, target);
    }
}
