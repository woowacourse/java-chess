package chess.domain.piece;

import static chess.domain.PieceScore.PAWN_WITHOUT_SAME_FILE;

import chess.domain.MoveStrategy;
import chess.domain.Position;
import chess.domain.Score;
import chess.domain.Team;

public class Pawn extends Piece {

    private static final int BLACK_INIT_RANK = 7;
    private static final int WHITE_INIT_RANK = 2;

    public Pawn(Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position source, Position target, Piece pieceInTarget) {
        if (this.isSameTeam(pieceInTarget)) {
            return false;
        }
        if (team == Team.BLACK) {
            return isBlackMovable(source, target, pieceInTarget);
        }
        return isWhiteMovable(source, target, pieceInTarget);
    }

    private boolean isBlackMovable(Position source, Position target, Piece pieceInTarget) {
        if (source.isSameRank(BLACK_INIT_RANK)) {
            return (MoveStrategy.BLACK_PAWN_FIRST.isMovable(source, target) && pieceInTarget.isSameTeam(Team.EMPTY))
                    || (MoveStrategy.BLACK_PAWN_CROSS.isMovable(source, target) && pieceInTarget.isSameTeam(Team.WHITE));
        }
        if (pieceInTarget.isSameTeam(Team.WHITE)) {
            return MoveStrategy.BLACK_PAWN_CROSS.isMovable(source, target);
        }
        return MoveStrategy.BLACK_PAWN_STRAIGHT.isMovable(source, target);
    }

    private boolean isWhiteMovable(Position source, Position target, Piece pieceInTarget) {
        if (source.isSameRank(WHITE_INIT_RANK)) {
            return (MoveStrategy.WHITE_PAWN_FIRST.isMovable(source, target) && pieceInTarget.isSameTeam(Team.EMPTY))
                    || (MoveStrategy.WHITE_PAWN_CROSS.isMovable(source, target) && pieceInTarget.isSameTeam(Team.BLACK));
        }
        if (pieceInTarget.isSameTeam(Team.BLACK)) {
            return MoveStrategy.WHITE_PAWN_CROSS.isMovable(source, target);
        }
        return MoveStrategy.WHITE_PAWN_STRAIGHT.isMovable(source, target);
    }

    @Override
    protected int calculateCount(int rankDiff, int fileDiff) {
        if (Math.abs(fileDiff) == Math.abs(rankDiff)) {
            return Math.abs(fileDiff);
        }
        return Math.abs(fileDiff + rankDiff);
    }

    @Override
    public Score convertToScore() {
        return new Score(PAWN_WITHOUT_SAME_FILE.getScore());
    }
}
