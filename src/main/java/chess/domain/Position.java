package chess.domain;

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
            .forEach(rank -> CACHE.put(makeKey(file, rank),
                new Position(file, rank)));
    }

    private static String makeKey(final File file, final Rank rank) {
        return file.toString() + rank.getValue();
    }

    public static Position valueOf(final String key) {
        Position position = CACHE.get(key);
        if (position == null) {
            throw new IllegalArgumentException(NOT_EXIST_POSITION);
        }
        return position;
    }

    public static Position valueOf(final File file, final Rank rank) {
        return valueOf(makeKey(file, rank));
    }

    public boolean isSameFile(Position position) {
        return file == position.file;
    }

    public boolean isSameRank(Position position) {
        return rank == position.rank;
    }

    public boolean isSameRank(final Rank rank) {
        return this.rank == rank;
    }

    public int getRankDifference(Position position) {
        return rank.getCoordinate() - position.rank.getCoordinate();
    }

    public int getFileDifference(Position position) {
        return file.getCoordinate() - position.file.getCoordinate();
    }

    public Position getIncreasedOneStepPosition(int xDifference, int yDifference) {
        int newX = file.getCoordinate() + xDifference;
        int newY = rank.getCoordinate() + yDifference;

        return Position.valueOf(File.findFile(newX), Rank.findRank(newY));
    }

    public File getFile() {
        return file;
    }
}
