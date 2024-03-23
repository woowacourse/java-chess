package chess.model.position;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class ChessPosition {
    private final File file;
    private final Rank rank;

    private ChessPosition(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static ChessPosition of(File file, Rank rank) {
        String key = file.name() + rank.name();
        return ChessPositionCache.CACHE.get(key);
    }

    public Movement calculateMovement(ChessPosition source) {
        Difference fileDifference = file.minus(source.file);
        Difference rankDifference = rank.minus(source.rank);
        return new Movement(fileDifference, rankDifference);
    }

    public boolean hasRank(Rank rank) {
        return this.rank == rank;
    }

    public ChessPosition calculateNextPosition(int fileOffset, int rankOffset) {
        File nextFile = file.calculateNextFile(fileOffset);
        Rank nextRank = rank.calculateNextRank(rankOffset);
        return new ChessPosition(nextFile, nextRank);
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
        ChessPosition that = (ChessPosition) o;
        return file == that.file && rank == that.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    private static class ChessPositionCache {
        static final Map<String, ChessPosition> CACHE = Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values())
                        .map(rank -> new ChessPosition(file, rank))
                ).collect(toMap(
                        chessPosition -> chessPosition.getFile().name() + chessPosition.getRank().name(),
                        identity())
                );

        private ChessPositionCache() {
        }
    }
}
