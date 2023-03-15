package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        int fileDiff = source.fileDiff(target);
        int rankDiff = source.rankDiff(target);

        return (Math.abs(fileDiff) == Math.abs(rankDiff))
                || (fileDiff == 0 || rankDiff == 0);
    }

    @Override
    public List<Position> findPath(Position source, Position target) {
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
