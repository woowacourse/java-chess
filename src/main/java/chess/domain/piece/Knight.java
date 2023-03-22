package chess.domain.piece;

import chess.domain.camp.TeamColor;
import chess.domain.move.KnightMove;

public class Knight extends Piece {

    private final KnightMove knightMove = new KnightMove();

    public Knight(final PieceType pieceType, final TeamColor teamColor) {
        super(pieceType, teamColor);
    }

    @Override
    public boolean canMove(final Position source, final Position target) {
        return knightMove.canMove(source, target);
    }
}
