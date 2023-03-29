package chess.domain.board.position;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position {

    private static final Map<String, Position> CACHE_POSITION;

    static {
        CACHE_POSITION = Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values())
                        .map(rank -> Map.entry(file.name() + rank.name(), new Position(file, rank)))
                ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final File file, final Rank rank) {
        return CACHE_POSITION.get(file.name() + rank.name());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return "Position{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }
}
