package chess.domain.position;

import static chess.domain.position.PositionConverter.charToMatchingInt;
import static chess.domain.position.PositionConverter.fileToIdx;
import static chess.domain.position.PositionConverter.rankToIdx;

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

    public static Position of(String value) {
        return PositionCache.getCache(value);
    }

    public boolean hasSameFileIdx(int fileIdx) {
        return this.fileIdx == fileIdx;
    }

    public int fileDifference(Position targetPosition) {
        return Math.abs(fileRawDifference(targetPosition));
    }

    public int fileRawDifference(Position targetPosition) {
        return targetPosition.fileIdx - fileIdx;
    }

    public int rankDifference(Position targetPosition) {
        return Math.abs(rankRawDifference(targetPosition));
    }

    public int rankRawDifference(Position targetPosition) {
        return targetPosition.rankIdx - rankIdx;
    }

    public boolean isSamePosition(Position position) {
        return this == position;
    }

    public static Position from(int fileIdx, int rankIdx) {
        return PositionCache.getCache(fileIdx, rankIdx);
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

    public double findRelativeDegree(Position target) {
        return Math.round(Math.atan2(fileRawDifference(target), rankRawDifference(target))
            * (180 / Math.PI));
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
