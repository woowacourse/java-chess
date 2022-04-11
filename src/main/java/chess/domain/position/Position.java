package chess.domain.position;

import java.util.HashMap;
import java.util.Map;

public class Position {

    private final File file;
    private final Rank rank;

    private Position(String value) {
        String[] positionInfo = value.split("");
        this.file = File.from(positionInfo[0]);
        this.rank = Rank.from(positionInfo[1]);
    }

    public static Position of(String value) {
        return PositionCache.getCache(value);
    }

    public static Position from(File file, Rank rank) {
        return PositionCache.getCache(file.getFileIdx(), rank.getRankIdx());
    }

    public boolean hasSameFileIdx(int targetFileIdx) {
        return file.getFileIdx() == targetFileIdx;
    }

    public int fileDifference(Position targetPosition) {
        return Math.abs(fileRawDifference(targetPosition));
    }

    public int fileRawDifference(Position targetPosition) {
        return file.rawDifference(getTargetFile(targetPosition));
    }

    private File getTargetFile(Position targetPosition) {
        return targetPosition.getFile();
    }

    public int rankDifference(Position targetPosition) {
        return Math.abs(rankRawDifference(targetPosition));
    }

    public int rankRawDifference(Position targetPosition) {
        return rank.rawDifference(getTargetRank(targetPosition));
    }

    private Rank getTargetRank(Position targetPosition) {
        return targetPosition.rank;
    }

    public boolean isSamePosition(Position position) {
        return this == position;
    }

    public static Position from(int fileIdx, int rankIdx) {
        return PositionCache.getCache(fileIdx, rankIdx);
    }

    public File getFile() {
        return file;
    }

    public int getFileIdx() {
        return file.getFileIdx();
    }

    public int getRankIdx() {
        return rank.getRankIdx();
    }

    public String getPosition() {
        return file.getRawFile() + rank.getRawRank();
    }

    @Override
    public String toString() {
        return "Position{" +
            "file=" + file +
            ", rank=" + rank +
            '}';
    }

    private static class PositionCache {

        static Map<String, Position> cache = new HashMap<>(64);

        static Position getCache(int fileIdx, int rankIdx) {
            String key = toKey(fileIdx, rankIdx);
            return cache.computeIfAbsent(key, (val) -> new Position(key));
        }

        static Position getCache(String key) {
            return cache.computeIfAbsent(key, (val) -> new Position(key));
        }

        static String toKey(int fileIdx, int rankIdx) {
            File file = File.of(fileIdx);
            Rank rank = Rank.of(rankIdx);

            String rawFile = file.getRawFile();
            String rawRank = rank.getRawRank();

            return rawFile + rawRank;
        }

    }

}
