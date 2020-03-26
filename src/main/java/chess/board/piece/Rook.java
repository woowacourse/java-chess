package chess.board.piece;

import chess.board.MoveInfo;

public class Rook extends Piece {

    private static final int ROOK_SCORE = 5;

    public Rook(final Team team) {
        super(team, ROOK_SCORE);
    }

    @Override
    public boolean canMove(final MoveInfo moveInfo, final Piece targetPiece) {
        if (targetPiece.isSameTeam(this.team)) {
            return false;
        }
        return moveInfo.isStraight();
    }
}
