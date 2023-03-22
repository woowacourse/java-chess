package chess.domain.piece;

import chess.domain.camp.TeamColor;
import chess.domain.move.KingMove;

public class King extends Piece {

    private static final KingMove kingMove = new KingMove();

    public King(final PieceType pieceType, final TeamColor teamColor) {
        super(pieceType, teamColor);
    }

    @Override
    public boolean canMove(final Position source, final Position target) {
        return kingMove.canMove(source, target);
    }
}
