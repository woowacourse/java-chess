package chess.domain.board.position;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Position {

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(File file, Rank rank) {
        return PositionCache.getCache(file, rank);
    }

    public static Position of(String positionKey) {
        return PositionCache.getCache(positionKey);
    }

    public int fileDistance(Position targetPosition) {
        int fileDifference = file.valueDifference(toFile(targetPosition));
        return Math.abs(fileDifference);
    }

    public int rankDistance(Position targetPosition) {
        int rankDifference = rank.valueDifference(toRank(targetPosition));
        return Math.abs(rankDifference);
    }

    public boolean hasRankOf(Rank rank) {
        return this.rank == rank;
    }

    public Position oneStepToward(Position targetPosition) {
        File nextFile = file.oneFileToward(toFile(targetPosition));
        Rank nextRank = rank.oneRankToward(toRank(targetPosition));

        return PositionCache.getCache(nextFile, nextRank);
    }

    public boolean checkDirection(Position targetPosition, Direction direction) {
        int x = file.valueDifference(toFile(targetPosition));
        int y = rank.valueDifference(toRank(targetPosition));

        return direction.hasAngleOf(x, y);
    }

    public String toKey() {
        return toKey(file, rank);
    }

    private File toFile(Position position) {
        return position.file;
    }

    private Rank toRank(Position position) {
        return position.rank;
    }

    private static String toKey(File file, Rank rank) {
        String fileKey = file.key();
        String rankKey = rank.key();
        return fileKey + rankKey;
    }

    @Override
    public String toString() {
        return "Position{" + toKey() + '}';
    }

    private static class PositionCache {

        static final int CACHE_CAPACITY = File.values().length * Rank.values().length;
        static final String INVALID_POSITION_RANGE_EXCEPTION_MESSAGE = "존재하지 않는 포지션입니다. (a1~h8)";

        static final int FILE_KEY_IDX = 0;
        static final int RANK_KEY_IDX = 1;
        static final Pattern VALID_POSITION_PATTERN = Pattern.compile("([abcdefgh][12345678])");

        static Map<String, Position> cache = new HashMap<>(CACHE_CAPACITY);

        static Position getCache(File file, Rank rank) {
            String key = toKey(file, rank);
            return cache.computeIfAbsent(key, (unused) -> new Position(file, rank));
        }

        static Position getCache(String positionKey) {
            validatePositionFormat(positionKey);

            String[] strings = positionKey.split("");
            File file = File.of(strings[FILE_KEY_IDX]);
            Rank rank = Rank.of(strings[RANK_KEY_IDX]);

            return getCache(file, rank);
        }

        static void validatePositionFormat(String position) {
            Matcher matcher = VALID_POSITION_PATTERN.matcher(position);
            if (!matcher.matches()) {
                throw new IllegalArgumentException(INVALID_POSITION_RANGE_EXCEPTION_MESSAGE);
            }
        }
    }
}
