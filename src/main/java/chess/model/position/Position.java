package chess.model.position;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class Position {
    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(File file, Rank rank) {
        int key = Objects.hash(file, rank);
        return PositionCache.CACHE.get(key);
    }

    public Movement calculateMovement(Position source) {
        Difference fileDifference = file.minus(source.file);
        Difference rankDifference = rank.minus(source.rank);
        return new Movement(fileDifference, rankDifference);
    }

    public boolean hasRank(Rank rank) {
        return this.rank == rank;
    }

    public Position calculateNextPosition(int fileOffset, int rankOffset) {
        File nextFile = file.calculateNextFile(fileOffset);
        Rank nextRank = rank.calculateNextRank(rankOffset);
        return Position.of(nextFile, nextRank);
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position that = (Position) o;
        return file == that.file && rank == that.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    private static class PositionCache {
        static final Map<Integer, Position> CACHE = Arrays.stream(File.values())
                .flatMap(PositionCache::createPositionStream)
                .collect(toMap(
                        position -> Objects.hash(position.getFile(), position.getRank()),
                        identity()));

        private static Stream<Position> createPositionStream(File file) {
            return Arrays.stream(Rank.values())
                    .map(rank -> new Position(file, rank));
        }

        private PositionCache() {
        }
    }
}
