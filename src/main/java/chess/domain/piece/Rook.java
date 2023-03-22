package chess.domain.piece;

import chess.domain.camp.TeamColor;
import chess.domain.move.RookMove;

public class Rook extends Piece {

    private final RookMove rookMove = new RookMove();

    public Rook(final PieceType pieceType, final TeamColor teamColor) {
        super(pieceType, teamColor);
    }

    @Override
    public boolean canMove(final Position source, final Position target) {
        return rookMove.canMove(source, target);
    }
}
