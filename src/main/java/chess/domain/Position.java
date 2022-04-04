package chess.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class Position {

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

    private static String makeKey(final File fileValue, final Rank rankValue) {
        return fileValue.toString().toLowerCase() + rankValue.getValue();
    }

    public static Position valueOf(final String key) {
        Position position = CACHE.get(key);
        if (position == null) {
            throw new IllegalArgumentException(NOT_EXIST_POSITION);
        }
        return position;
    }

    public static Position valueOf(final File abscissa, final Rank ordinate) {
        return valueOf(makeKey(abscissa, ordinate));
    }

    public boolean isSameFile(Position position) {
        return file == position.file;
    }

    public boolean isSameRank(final Rank ordinate) {
        return this.rank == ordinate;
    }

    public int getRankDifference(Position position) {
        return rank.getRank() - position.rank.getRank();
    }

    public int getFileDifference(Position position) {
        return file.getFile() - position.file.getFile();
    }

    public double getAngle(Position toPosition) {
        int x = toPosition.file.getFile() - this.file.getFile();
        int y = toPosition.rank.getRank() - this.rank.getRank();
        return Math.atan2(y, x);
    }

    public Position getIncreasedOneStepPosition(int xDifference, int yDifference) {
        int newX = file.getFile() + xDifference;
        int newY = rank.getRank() + yDifference;

        return Position.valueOf(File.findFile(newX), Rank.findRank(newY));
    }

    public File getFile() {
        return file;
    }

    public String toString() {
        return file.toString().toLowerCase() + rank.getValue();
    }
}
