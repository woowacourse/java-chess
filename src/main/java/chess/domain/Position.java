package chess.domain;

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
            POSITIONS.put(file.getFile() + rank.getRank(), new Position(file, rank));
        }
    }

    public static Position of(String position) {
        return POSITIONS.get(position);
    }
}
