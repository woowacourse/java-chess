package chess.model;

import static java.util.stream.Collectors.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position {

    static final String ERROR_INVALID_POSITION_INPUT = "[ERROR]: 올바르지 않은 위치 입력입니다.";
    private static final Map<String, Position> CACHE = new HashMap<>();

    static {
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                CACHE.put(file.getValue() + rank.getValue(), new Position(file, rank));
            }
        }
    }

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(File file, Rank rank) {
        return CACHE.get(file.getValue() + rank.getValue());
    }

    public static Position of(String fileRank) {
        if (!CACHE.containsKey(fileRank)) {
            throw new IllegalArgumentException(ERROR_INVALID_POSITION_INPUT);
        }
        return CACHE.get(fileRank);
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
            .map(file -> Position.of(file, this.rank))
            .collect(Collectors.toList());
    }

    private List<Position> straightHorizontalRanks(List<Rank> ranks) {
        return ranks.stream()
            .map(rank -> Position.of(this.file, rank))
            .collect(Collectors.toList());
    }

    private List<Position> positionsBetweenTwoFiles(List<File> files, Rank rank) {
        return files.stream()
            .map(file -> Position.of(file, rank))
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
