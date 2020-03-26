package chess.board.piece;

import chess.board.MoveInfo;

public class Queen extends Piece {

    private static final int QUEEN_SCORE = 9;

    public Queen(final Team team) {
        super(team, QUEEN_SCORE);
    }

    @Override
    public boolean canMove(final MoveInfo moveInfo, final Piece targetPiece) {
        if (targetPiece.isSameTeam(this.team)) {
            return false;
        }
        return moveInfo.isDiagonal() || moveInfo.isStraight();
    }
}
