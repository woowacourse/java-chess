package chess.domain.piece;

import chess.domain.camp.TeamColor;
import chess.domain.move.PawnMove;

public class Pawn extends Piece {

    private final PawnMove pawnMove = new PawnMove();

    public Pawn(final PieceType pieceType, final TeamColor teamColor) {
        super(pieceType, teamColor);
    }

    @Override
    public boolean canMove(final Position source, final Position target) {
        return pawnMove.canMove(source, target);
    }
}
