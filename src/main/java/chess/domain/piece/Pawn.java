package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;

import static chess.domain.MoveStrategy.*;

public class Pawn extends Piece {

    private static final int BLACK_INIT_RANK = 7;
    private static final int WHITE_INIT_RANK = 2;

    public Pawn(Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position source, Position target, Team team) {
        if (this.isSameTeam(team)) {
            throw new IllegalArgumentException("[ERROR] 타겟 위치에 같은 팀 기물이 있습니다.");
        }
        if (team == Team.EMPTY) {
            return isMovableToEmpty(source, target);
        }
        return isMovableToEnemy(source, target);
    }

    private boolean isMovableToEmpty(Position source, Position target) {
        if (team == Team.BLACK) {
            return isBlackMovableToEmpty(source, target);
        }
        return isWhiteMovableToEmpty(source, target);
    }

    private boolean isBlackMovableToEmpty(Position source, Position target) {
        return (isFirstMoveBlackPawn(source) && BLACK_PAWN_FIRST.isMovable(source, target))
                || BLACK_PAWN_NORMAL.isMovable(source, target);
    }

    private boolean isFirstMoveBlackPawn(Position source) {
        return source.isSameRank(BLACK_INIT_RANK);
    }

    private boolean isWhiteMovableToEmpty(Position source, Position target) {
        return (isFirstMoveWhitePawn(source) && WHITE_PAWN_FIRST.isMovable(source, target))
                || WHITE_PAWN_NORMAL.isMovable(source, target);
    }

    private boolean isFirstMoveWhitePawn(Position source) {
        return source.isSameRank(WHITE_INIT_RANK);
    }

    private boolean isMovableToEnemy(Position source, Position target) {
        if (team == Team.BLACK) {
            return isBlackMovableToEnemy(source, target);
        }
        return isWhiteMovableToEnemy(source, target);
    }

    private boolean isBlackMovableToEnemy(Position source, Position target) {
        return BLACK_PAWN_ENEMY.isMovable(source, target);
    }

    private boolean isWhiteMovableToEnemy(Position source, Position target) {
        return WHITE_PAWN_ENEMY.isMovable(source, target);
    }

    @Override
    protected int calculateCount(int rankDiff, int fileDiff) {
        if (Math.abs(fileDiff) == Math.abs(rankDiff)) {
            return Math.abs(fileDiff);
        }
        return Math.abs(fileDiff + rankDiff);
    }
}
