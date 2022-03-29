package chess.model;

import static java.lang.String.*;
import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        List<Rank> ranks = rank.betweenRanks(other.rank);
        List<File> files = file.betweenFiles(other.file);

        if (ranks.isEmpty()) {
            return files.stream().map(file -> new Position(this.rank, file)).collect(Collectors.toList());
        }

        if (files.isEmpty()) {
            return ranks.stream().map(rank -> new Position(rank, this.file)).collect(Collectors.toList());
        }

        return ranks.stream()
            .flatMap(rank -> positionsBetweenTwoFiles(files, rank).stream())
            .collect(toList());
    }

    private List<Position> positionsBetweenTwoFiles(List<File> files, Rank rank) {
        return files.stream().map(file -> new Position(rank, file)).collect(toList());
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
