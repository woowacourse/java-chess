package chess.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Position {
    private static final Map<String, Position> POSITIONS = new HashMap<>();

    static {
        for (final File file : File.values()) {
            makePositionWith(file);
        }
    }

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    private static void makePositionWith(File file) {
        for (final Rank rank : Rank.values()) {
            POSITIONS.put(makeKey(file, rank), new Position(file, rank));
        }
    }

    public static Position of(String position) {
        return POSITIONS.get(position);
    }

    public static Position of(File file, Rank rank) {
        return POSITIONS.get(makeKey(file, rank));
    }

    private static String makeKey(File file, Rank rank) {
        return file.getFile() + rank.getRank();
    }

    public Direction calculateDirection(Position target) {
        return Direction.of(calculateFilePoint(target.getX()), calculateRankPoint(target.getY()));
    }

    private int calculateFilePoint(int x) {
        return x - this.getX() / Math.abs(x - this.getX());
    }

    private int calculateRankPoint(int y) {
        return y - this.getY() / Math.abs(y - this.getY());
    }

    public int getX() {
        return file.getIndex();
    }

    public int getY() {
        return rank.getRank();
    }
}
