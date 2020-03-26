package chess.board.piece;

import chess.board.MoveInfo;
import chess.board.Rank;

public class Pawn extends Piece {

    private static final int PAWN_SCORE = 1;

    public Pawn(final Team team) {
        super(team, PAWN_SCORE);
    }

    @Override
    public boolean canMove(final MoveInfo moveInfo, final Piece targetPiece) {
        if (targetPiece.isSameTeam(this.team)) {
            return false;
        }
        if (moveInfo.isStraight() && targetPiece.isBlank() && isSameDirection(moveInfo, 2)) {
            if (team == Team.WHITE && moveInfo.isSameRank(Rank.TWO) || team == Team.BLACK && moveInfo.isSameRank(Rank.SEVEN)) {
                return true;
            }
        }
        if (!isSameDirection(moveInfo, 1)) {
            return false;
        }
        if (targetPiece.isBlank()) {
            return moveInfo.isStraight();
        }
        return moveInfo.isDiagonal();
    }

    private boolean isSameDirection(final MoveInfo moveInfo, int value) {
        return moveInfo.isRangeUnderAbsolute(value) && team.isSameDirection(moveInfo.getRankVariation());
    }
}
