package chess.domain.piece;

import chess.domain.Position;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    @Override
    boolean isMovable(Position source, Position target) {
        int fileDiff = source.fileDiff(target);
        int rankDiff = source.rankDiff(target);

        return fileDiff == 0 || rankDiff == 0;
    }

    @Override
    List<Position> findPath(Position source, Position target) {
        int rankDiff = source.rankDiff(target);
        int fileDiff = source.fileDiff(target);

        int count = Math.abs(fileDiff + rankDiff);
        int rankUnit = rankDiff / count;
        int fileUnit = fileDiff / count;

        return makePath(count, rankUnit, fileUnit, source);
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
