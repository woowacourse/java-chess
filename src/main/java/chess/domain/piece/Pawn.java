package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private static final int BLACK_INIT_RANK = 7;
    private static final int WHITE_INIT_RANK = 2;

    public Pawn(Team team) {
        super(team);
    }

    @Override
    boolean isMovable(Position source, Position target) {
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
    List<Position> findPath(Position source, Position target) {
        int rankDiff = source.rankDiff(target);
        int fileDiff = source.fileDiff(target);

        int count = initCount(rankDiff, fileDiff);
        int rankUnit = rankDiff / count;
        int fileUnit = fileDiff / count;

        return makePath(count, rankUnit, fileUnit, source);
    }

    private int initCount(int rankDiff, int fileDiff) {
        int count = 1;
        if (Math.abs(fileDiff) == Math.abs(rankDiff)) {
            count = Math.abs(fileDiff);
        }
        if (fileDiff == 0 || rankDiff == 0) {
            count = Math.abs(fileDiff + rankDiff);
        }

        return count;
    }

    private List<Position> makePath(int count, int rankUnit, int fileUnit, Position current) {
        List<Position> path = new ArrayList<>();

        for(int i = 0; i < count; i++) {
            current = current.moveBy(rankUnit, fileUnit);
            path.add(current);
        }

        return path;
    }
}
