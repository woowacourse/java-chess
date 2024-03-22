package chess.domain.attribute;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import chess.domain.chessboard.attribute.Direction;

public class Position {

    private static final Map<String, Position> POSITIONS = new HashMap<>();

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final String position) {
        return of(File.of(position.charAt(0)), Rank.of(position.charAt(1)));
    }

    public static Position of(final Rank rank, final File file) {
        return of(file, rank);
    }

    public static Position of(final File file, final Rank rank) {
        if (POSITIONS.isEmpty()) {
            initializePositions();
        }
        return POSITIONS.get(keyOf(file, rank));
    }

    private static void initializePositions() {
        for (final Rank rank : Rank.values()) {
            putPositions(rank);
        }
    }

    private static void putPositions(final Rank rank) {
        for (final File file : File.values()) {
            POSITIONS.put(keyOf(file, rank), new Position(file, rank));
        }
    }

    private static String keyOf(final File file, final Rank rank) {
        return file.name() + rank.name();
    }

    public static boolean isInRange(final int column, final int row) {
        return File.isInRange(column) && Rank.isInRange(row);
    }

    public Optional<Position> moveTo(final Direction direction) {
        int row = rank.toRow() + direction.getRow();
        int column = file.toColumn() + direction.getColumn();
        if (isInRange(column, row)) {
            return Optional.of(Position.of(File.of(column), Rank.of(row)));
        }
        return Optional.empty();
    }

    public File file() {
        return file;
    }

    public Rank rank() {
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

    @Override
    public String toString() {
        return "Position{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
