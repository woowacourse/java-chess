package chess.domain.position;

import static chess.domain.position.util.PositionUtil.RANKS_TOTAL_SIZE;

import chess.domain.position.util.PositionUtil;
import java.util.HashMap;
import java.util.Map;

public class Position {

    private final int fileIdx;
    private final int rankIdx;

    private Position(String value) {
        char[] positionInfo = value.toCharArray();
        this.fileIdx = PositionUtil.charToMatchingInt(positionInfo[0]);
        this.rankIdx = PositionUtil.charToMatchingInt(positionInfo[1]);
    }

    public static Position of(int fileIdx, int rankIdx) {
        return PositionCache.getCache(fileIdx, rankIdx);
    }

    public static Position of(String value) {
        return PositionCache.getCache(value);
    }

    public boolean hasSameFileIdx(int fileIdx) {
        return this.fileIdx == fileIdx;
    }

    public int fileDifference(Position targetPosition) {
        return Math.abs(fileIdx - targetPosition.fileIdx);
    }

    public int rankDifference(Position targetPosition) {
        return Math.abs(rankRawDifference(targetPosition));
    }

    public int rankRawDifference(Position targetPosition) {
        return targetPosition.rankIdx - rankIdx;
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

    public Position movedBy(int fileDiff, int rankDiff) {
        int toFileIdx = fileIdx + fileDiff;
        int toRankIdx = rankIdx + rankDiff;
        return PositionCache.getCache(toFileIdx, toRankIdx);
    }

    public int getFileIdx() {
        return fileIdx;
    }

    public int toDisplayRowIndex() {
        return RANKS_TOTAL_SIZE - rankIdx - 1;
    }

    public boolean hasRankIdxOf(int rankIdx) {
        return this.rankIdx == rankIdx;
    }

    @Override
    public String toString() {
        return "Position{" + fileIdx + "" + rankIdx + '}';
    }

    private static class PositionCache {

        static Map<String, Position> cache = new HashMap<>(64);

        static Position getCache(int fileIdx, int rankIdx) {
            String key = fileIdx + "" + rankIdx;
            return cache.computeIfAbsent(key, (val) -> new Position(key));
        }

        static Position getCache(String value) {
            String key = toKey(value);
            return cache.computeIfAbsent(key, (val) -> new Position(key));
        }

        static String toKey(String value) {
            char[] positionInfo = value.toCharArray();
            int fileIdx = PositionUtil.fileToIdx(positionInfo[0]);
            int rankIdx = PositionUtil.rankToIdx(positionInfo[1]);
            return fileIdx + "" + rankIdx;
        }
    }
}
