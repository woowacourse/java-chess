package chess.domain.position;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Position {
    private static final Map<String, Position> CACHE = new ConcurrentHashMap<>();

    private final ChessFile file;
    private final ChessRank rank;

    private Position(final ChessFile file, final ChessRank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final ChessFile file, final ChessRank rank) {
        return CACHE.computeIfAbsent(file.name() + rank.name(),
                key -> new Position(file, rank));
    }

    public Set<Position> findBetween(final Position target) {
        List<ChessRank> betweenRanks = ChessRank.findBetween(this.rank, target.rank);
        List<ChessFile> betweenFiles = ChessFile.findBetween(this.file, target.file);

        if (betweenRanks.isEmpty()) {
            return findHorizontalPositions(betweenFiles);
        }
        if (betweenFiles.isEmpty()) {
            return findVerticalPositions(betweenRanks);
        }
        return findDiagonalPositions(betweenFiles, betweenRanks);
    }

    private Set<Position> findHorizontalPositions(final List<ChessFile> betweenFiles) {
        Set<Position> positions = new HashSet<>();
        for (ChessFile file : betweenFiles) {
            positions.add(new Position(file, this.rank));
        }
        return positions;
    }

    private Set<Position> findVerticalPositions(final List<ChessRank> betweenRanks) {
        Set<Position> positions = new HashSet<>();
        for (ChessRank rank : betweenRanks) {
            positions.add(new Position(this.file, rank));
        }
        return positions;
    }

    private Set<Position> findDiagonalPositions(final List<ChessFile> betweenFiles, final List<ChessRank> betweenRanks) {
        Set<Position> positions = new HashSet<>();
        for (int i = 0; i < betweenFiles.size(); i++) {
            positions.add(new Position(betweenFiles.get(i), betweenRanks.get(i)));
        }
        return positions;
    }

    public int calculateDistanceTo(final Position target) {
        int fileDistance = calculateFileDistanceTo(target);
        int rankDistance = calculateRankDistanceTo(target);

        if (fileDistance > 0) {
            return fileDistance;
        }
        return rankDistance;
    }

    public int calculateFileDistanceTo(final Position target) {
        return Math.abs(target.file.index() - file.index());
    }

    public int calculateRankDistanceTo(final Position target) {
        return Math.abs(target.rank.index() - rank.index());
    }

    public boolean isRank(final ChessRank rank) {
        return this.rank == rank;
    }

    public int indexOfFile() {
        return file.index();
    }

    public int indexOfRank() {
        return rank.index();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
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
}
