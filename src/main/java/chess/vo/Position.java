package chess.vo;

import static java.lang.String.*;
import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Position {

    private static final int INDEX_FILE = 0;
    private static final int INDEX_RANK = 1;

    private final Rank rank;
    private final File file;

    public Position(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    public Position(String rankFile) {
        this(Rank.of(valueOf(rankFile.charAt(INDEX_RANK))),
            File.of(valueOf(rankFile.charAt(INDEX_FILE))));
    }

    public boolean isRankOf(Rank otherRank) {
        return rank == otherRank;
    }

    public boolean isSameRank(Position other) {
        return other.isRankOf(rank);
    }

    public int rankDistance(Position other) {
        return Math.abs(rank.displacement(other.rank));
    }

    public int fileDistance(Position other) {
        return Math.abs(file.displacement(other.file));
    }

    public int rankDisplacement(Position other) {
        return rank.displacement(other.rank);
    }

    public boolean isSameFile(Position other) {
        return this.file == other.file;
    }

    public List<Position> between(Position other) {
        return Rank.reverseValues()
            .stream()
            .flatMap(rank -> betweenOneFile(other, rank).stream())
            .collect(toList());
    }

    private List<Position> betweenOneFile(Position other, Rank rank) {
        return Arrays.stream(File.values())
            .filter(file -> rank.isBetween(this.rank, other.rank) && file.isBetween(this.file, other.file))
            .map(file -> new Position(rank, file))
            .collect(toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Position position = (Position)o;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    @Override
    public String toString() {
        return "Position{" +
            "rank=" + rank +
            ", file=" + file +
            '}';
    }
}
