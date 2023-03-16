package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;
import java.util.List;

public class Pawn extends Piece {

    private static final int BLACK_INIT_RANK = 7;
    private static final int WHITE_INIT_RANK = 2;

    public Pawn(Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        if (team == Team.BLACK) {
            return isBlackMovable(source, target);
        }
        return isWhiteMovable(source ,target);
    }

    private boolean isBlackMovable(Position source, Position target) {
        int fileDiff = source.fileDiff(target);
        int rankDiff = source.rankDiff(target);

        if (rankDiff == -1 && Math.abs(fileDiff) <= 1) {
            return true;
        }
        return source.isSameRank(BLACK_INIT_RANK)
                && fileDiff == 0 && rankDiff == -2;
    }

    private boolean isWhiteMovable(Position source, Position target) {
        int fileDiff = source.fileDiff(target);
        int rankDiff = source.rankDiff(target);

        if (rankDiff == 1 && Math.abs(fileDiff) <= 1) {
            return true;
        }
        return source.isSameRank(WHITE_INIT_RANK)
                && fileDiff == 0 && rankDiff == 2;
    }

    @Override
    public List<Position> findPath(Position source, Position target) {
        return findPathTemplate(source, target, this::calculateCount);
    }

    @Override
    protected int calculateCount(int rankDiff, int fileDiff) {
        if (Math.abs(fileDiff) == Math.abs(rankDiff)) {
            return Math.abs(fileDiff);
        }
        return Math.abs(fileDiff + rankDiff);
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
