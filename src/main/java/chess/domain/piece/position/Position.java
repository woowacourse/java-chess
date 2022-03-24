package chess.domain.piece.position;

import static chess.domain.piece.position.PositionUtil.charToMatchingInt;
import static chess.domain.piece.position.PositionUtil.fileToIdx;
import static chess.domain.piece.position.PositionUtil.rankToIdx;

import java.util.HashMap;
import java.util.Map;

public class Position {

    private final int fileIdx;
    private final int rankIdx;

    private Position(String value) {
        char[] positionInfo = value.toCharArray();
        this.fileIdx = charToMatchingInt(positionInfo[0]);
        this.rankIdx = charToMatchingInt(positionInfo[1]);
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

    public int getRankIdx() {
        return rankIdx;
    }

    @Override
    public String toString() {
        return "Position{" +
                "fileIdx=" + fileIdx +
                ", rankIdx=" + rankIdx +
                '}';
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
            int fileIdx = fileToIdx(positionInfo[0]);
            int rankIdx = rankToIdx(positionInfo[1]);
            return fileIdx + "" + rankIdx;
        }
    }
}
