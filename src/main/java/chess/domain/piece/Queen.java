package chess.domain.piece;

import chess.domain.camp.TeamColor;
import chess.domain.move.QueenMove;

public class Queen extends Piece {

    private final QueenMove queenMove = new QueenMove();

    public Queen(final PieceType pieceType, final TeamColor teamColor) {
        super(pieceType, teamColor);
    }

    @Override
    public boolean canMove(final Position source, final Position target) {
        return queenMove.canMove(source, target);
    }
}
