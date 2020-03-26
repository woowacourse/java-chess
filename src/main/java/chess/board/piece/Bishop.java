package chess.board.piece;

import chess.board.MoveInfo;

public class Bishop extends Piece {

    private static final int BISHOP_SCORE = 3;

    public Bishop(final Team team) {
        super(team, BISHOP_SCORE);
    }

    @Override
    public boolean canMove(final MoveInfo moveInfo, final Piece targetPiece) {
        if (targetPiece.isSameTeam(this.team)) {
            return false;
        }
        return moveInfo.isDiagonal();
    }
}
