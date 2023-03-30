package chess.domain.position;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Position {

    private final File file;
    private final Rank rank;

    private static final Map<String, Position> CACHE;

    static {
        CACHE = Arrays.stream(Rank.values())
                .flatMap(Position::createPositionsFromOneFile)
                .collect(Collectors.toMap(position -> position.file.name() + position.rank.name(),
                        Function.identity()));
    }

    private static Stream<Position> createPositionsFromOneFile(final Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> new Position(file, rank));
    }

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(File file, Rank rank) {
        return CACHE.get(file.name() + rank.name());
    }

    public static Position from(String rawPosition) {
        File file = File.valueOf(rawPosition.substring(0, 1).toUpperCase());
        Rank rank = Rank.from(Integer.parseInt(rawPosition.substring(1)));
        return Position.of(file, rank);
    }

    public int calculateFileDistance(final Position source) {
        return rank.calculateDistance(source.rank);
    }

    public int calculateRankDistance(final Position source) {
        return file.calculateDistance(source.file);
    }

    public Position move(final int rankDirection, final int fileDirection) {
        return Position.of(file.plus(rankDirection), rank.plus(fileDirection));
    }

    public boolean isSameRank(final Rank rank) {
        return this.rank == rank;
    }

    public boolean isSameFile(final File file) {
        return this.file == file;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }
}
