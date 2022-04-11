package chess.domain.position;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Position {

    private static final int RANK_INDEX = 1;
    private static final int FILE_INDEX = 0;
    private static final Map<String, Position> cache = new HashMap<>();

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final File file, final Rank rank) {
        final String position = file.getName() + rank.getRank();
        return from(position);
    }

    public static Position from(final String position) {
        final var cachedPosition = cache.get(position);

        if (cachedPosition == null) {
            final File file = File.from(position.substring(FILE_INDEX, RANK_INDEX));
            final Rank rank = Rank.from(position.substring(RANK_INDEX));
            final Position newPosition = new Position(file, rank);
            cache.put(position, newPosition);
            return newPosition;
        }
        return cachedPosition;
    }

    public boolean isSameFile(final File other) {
        return this.file == other;
    }

    public Position nextPosition(final int fileGrowth, final int rankGrowth) {
        return Position.of(File.findByOrder(file.getOrder() + fileGrowth), Rank.from(rank.getRank() + rankGrowth));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;

        Position position = (Position) o;

        if (file != position.file) return false;
        return rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public int getRankNumber() {
        return rank.getRank();
    }

    public File getFile() {
        return file;
    }

    public int getFileOrder() {
        return file.getOrder();
    }
}
