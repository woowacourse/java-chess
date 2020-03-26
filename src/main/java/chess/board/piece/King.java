package chess.board.piece;

import chess.board.MoveInfo;

public class King extends Piece {

    private static final int KING_SCORE = 0;

    public King(final Team team) {
        super(team, KING_SCORE);
    }

    @Override
    public boolean canMove(final MoveInfo moveInfo, final Piece targetPiece) {
        if (targetPiece.isSameTeam(this.team)) {
            return false;
        }
        return moveInfo.isRangeUnderAbsolute(1);
    }
}
