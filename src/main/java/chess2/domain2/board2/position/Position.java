package chess2.domain2.board2.position;

import chess2.util2.PositionConverterUtil;
import java.util.HashMap;
import java.util.Map;

public class Position {

    public static final int FILES_TOTAL_SIZE = 8;
    public static final int RANKS_TOTAL_SIZE = 8;

    private static final String INVALID_IDX_RANGE_EXCEPTION_MESSAGE = "존재하지 않는 위치입니다. (index 범위: 0~7)";

    private final int fileIdx;
    private final int rankIdx;

    private Position(int fileIdx, int rankIdx) {
        validateIdxRange(fileIdx, rankIdx);
        this.fileIdx = fileIdx;
        this.rankIdx = rankIdx;
    }

    private static void validateIdxRange(int fileIdx, int rankIdx) {
        boolean invalidFileIdxRange = fileIdx < 0 || fileIdx >= FILES_TOTAL_SIZE;
        boolean invalidRankIdxRange = rankIdx < 0 || rankIdx >= RANKS_TOTAL_SIZE;

        if (invalidFileIdxRange || invalidRankIdxRange) {
            throw new IllegalArgumentException(INVALID_IDX_RANGE_EXCEPTION_MESSAGE);
        }
    }

    public static Position of(int fileIdx, int rankIdx) {
        return PositionCache.getCache(fileIdx, rankIdx);
    }

    public static Position of(String positionKey) {
        int fileIdx = PositionConverterUtil.toFileIdx(positionKey);
        int rankIdx = PositionConverterUtil.toRankIdx(positionKey);
        return Position.of(fileIdx, rankIdx);
    }

    public int fileDifference(Position targetPosition) {
        return Math.abs(fileIdx - targetPosition.fileIdx);
    }

    public int rankDifference(Position targetPosition) {
        return Math.abs(targetPosition.rankIdx - rankIdx);
    }

    public boolean hasRankIdxOf(int rankIdx) {
        return this.rankIdx == rankIdx;
    }

    public Position oneStepToward(Position targetPosition) {
        int nextFileIdx = incrementToward(fileIdx, targetPosition.fileIdx);
        int nextRankIdx = incrementToward(rankIdx, targetPosition.rankIdx);

        return PositionCache.getCache(nextFileIdx, nextRankIdx);
    }

    private int incrementToward(int from, int to) {
        return from + Integer.compare(to, from);
    }

    public boolean checkDirection(Position targetPosition, Direction direction) {
        int fileDiff =  targetPosition.fileIdx - fileIdx;
        int rankDiff =  targetPosition.rankIdx - rankIdx;

        return direction.checkByPositionDifference(fileDiff, rankDiff);
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

        static String toKey(int fileIdx, int rankIdx) {
            return fileIdx + "" + rankIdx;
        }
    }
}
