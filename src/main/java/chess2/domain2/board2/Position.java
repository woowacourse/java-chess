package chess2.domain2.board2;

import chess2.util2.PositionUtil;
import java.util.HashMap;
import java.util.Map;

public class Position {

    private final int fileIdx;
    private final int rankIdx;

    private Position(int fileIdx, int rankIdx) {
        this.fileIdx = fileIdx;
        this.rankIdx = rankIdx;
    }

    public static Position of(int fileIdx, int rankIdx) {
        return PositionCache.getCache(fileIdx, rankIdx);
    }

    public static Position of(String positionKey) {
        int fileIdx = PositionUtil.toFileIdx(positionKey);
        int rankIdx = PositionUtil.toRankIdx(positionKey);
        return Position.of(fileIdx, rankIdx);
    }

    public int fileDifference(Position targetPosition) {
        return Math.abs(fileIdx - targetPosition.fileIdx);
    }

    public int rankDifference(Position targetPosition) {
        return Math.abs(targetPosition.rankIdx - rankIdx);
    }

    public boolean isHorizontal(Position toPosition) {
        return fileDifference(toPosition) == 0
                && rankDifference(toPosition) > 0;
    }

    public boolean isVertical(Position toPosition) {
        return fileDifference(toPosition) > 0
                && rankDifference(toPosition) == 0;
    }

    public boolean isDiagonal(Position toPosition) {
        return fileDifference(toPosition) == rankDifference(toPosition);
    }

    public boolean isStraightPath(Position toPosition) {
        return isHorizontal(toPosition) || isVertical(toPosition) || isDiagonal(toPosition);
    }

    @Override
    public String toString() {
        return "Position{fileIdx=" + fileIdx + ", rankIdx=" + rankIdx + '}';
    }

    private static class PositionCache {

        static Map<String, Position> cache = new HashMap<>(64);

        static Position getCache(int fileIdx, int rankIdx) {
            String key = toKey(fileIdx, rankIdx);
            return cache.computeIfAbsent(key, (k) -> new Position(fileIdx, rankIdx));
        }

        private static String toKey(int fileIdx, int rankIdx) {
            return fileIdx + "" + rankIdx;
        }
    }
}
