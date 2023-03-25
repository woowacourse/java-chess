package chess.model.position;

import chess.model.piece.Direction;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position {

    private static final Map<File, RankPosition> CACHE;

    static {
        CACHE = Arrays.stream(File.values())
                .collect(Collectors.toMap(file -> file, RankPosition::create));
    }

    Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    private final File file;
    private final Rank rank;

    public static Position of(final File file, final Rank rank) {
        final RankPosition rankPosition = CACHE.get(file);

        return rankPosition.findByRank(rank);
    }

    public static RankPosition findRankPositionByFile(final File file) {
        return CACHE.get(file);
    }

    public Distance differ(final Position other) {
        final int differRank = this.rank.differ(other.rank);
        final int differFile = this.file.differ(other.file);

        return new Distance(differFile, differRank);
    }

    public Position findNextPosition(final Direction direction) {
        final Rank nextRank = direction.findNextRank(rank);
        final File nextFile = direction.findNextFile(file);

        return of(nextFile, nextRank);
    }

    public String getPosition() {
        return file.name() + rank.value();
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
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
