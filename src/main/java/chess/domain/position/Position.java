package chess.domain.position;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Position {

    private static final String NOT_EXIST_POSITION = "[ERROR] 해당 포지션은 체스 보드에 존재하지 않습니다.";
    private static final Map<String, Position> CACHE = new HashMap<>();

    private final File file;
    private final Rank rank;

    static {
        Arrays.stream(File.values())
            .forEach(Position::savePosition);
    }

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    private static void savePosition(final File file) {
        Arrays.stream(Rank.values())
            .forEach(rank -> CACHE.put(makeKey(file, rank), new Position(file, rank)));
    }

    private static String makeKey(final File file, final Rank rank) {
        return file.name().toLowerCase() + rank.getValue();
    }

    public static Position valueOf(final String key) {
        if (!CACHE.containsKey(key)) {
            throw new IllegalArgumentException(NOT_EXIST_POSITION);
        }
        return CACHE.get(key);
    }

    public static Position valueOf(final File file, final Rank rank) {
        return valueOf(makeKey(file, rank));
    }

    public boolean isCross(final Position position) {
        return isSameFile(position) || isSameRank(position);
    }

    public boolean isDiagonal(final Position position) {
        return Math.abs(getRankDifference(position)) == Math.abs(getFileDifference(position));
    }

    public boolean isSameFile(final Position position) {
        return file == position.file;
    }

    public boolean isSameRank(final Position position) {
        return rank == position.rank;
    }

    public boolean isSameRank(final Rank rank) {
        return this.rank == rank;
    }

    public int getRankDifference(final Position position) {
        return rank.getValue() - position.rank.getValue();
    }

    public int getFileDifference(final Position position) {
        return file.getValue() - position.file.getValue();
    }

    public Position getIncreasedPositionByDifference(final int fileDifference, final int rankDifference) {
        final int newFile = file.getValue() + fileDifference;
        final int newRank = rank.getValue() + rankDifference;

        return Position.valueOf(File.findFile(newFile), Rank.findRank(newRank));
    }

    public File getFile() {
        return file;
    }
}
