package chess.domain.attribute;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

    private static final Map<String, Position> POSITIONS = new HashMap<>();

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final File file, final Rank rank) {
        if (POSITIONS.isEmpty()) {
            createPositions();
        }
        return POSITIONS.get(keyOf(file, rank));
    }

    private static void createPositions() {
        for (final Rank rank : Rank.values()) {
            createPositionsOf(rank);
        }
    }

    private static void createPositionsOf(final Rank rank) {
        for (final File file : File.values()) {
            POSITIONS.put(keyOf(file, rank), new Position(file, rank));
        }
    }

    private static String keyOf(final File file, final Rank rank) {
        return file.name() + rank.name();
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        return object instanceof Position other
                && rank == other.rank
                && file == other.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
