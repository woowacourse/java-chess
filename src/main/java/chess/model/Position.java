package chess.model;

import static java.lang.String.*;
import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position {

    private static final int INDEX_FILE = 0;
    private static final int INDEX_RANK = 1;
    private static final Map<Rank, Map<File, Position>> CACHE = new HashMap<>();

    static {
        for (Rank rank : Rank.values()) {
            CACHE.put(rank, Arrays.stream(File.values())
                .collect(toUnmodifiableMap(file -> file, file -> new Position(rank, file))));
        }
    }

    private final Rank rank;
    private final File file;

    private Position(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    public static Position of(Rank rank, File file) {
        return CACHE.get(rank).get(file);
    }

    public static Position of(String rankFile) {
        return CACHE.get(Rank.of(valueOf(rankFile.charAt(INDEX_RANK))))
            .get(File.of(valueOf(rankFile.charAt(INDEX_FILE))));
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
            return straightVerticalFiles(files);
        }

        if (files.isEmpty()) {
            return straightHorizontalRanks(ranks);
        }

        return ranks.stream()
            .flatMap(rank -> positionsBetweenTwoFiles(files, rank).stream())
            .collect(toList());
    }

    private List<Position> straightVerticalFiles(List<File> files) {
        return files.stream()
            .map(file -> Position.of(this.rank, file))
            .collect(Collectors.toList());
    }

    private List<Position> straightHorizontalRanks(List<Rank> ranks) {
        return ranks.stream()
            .map(rank -> Position.of(rank, this.file))
            .collect(Collectors.toList());
    }

    private List<Position> positionsBetweenTwoFiles(List<File> files, Rank rank) {
        return files.stream()
            .map(file -> Position.of(rank, file))
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
