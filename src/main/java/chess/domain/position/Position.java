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

    private Position(ChessFile file, ChessRank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(String position) {
        return CACHE.computeIfAbsent(position, key -> new Position(
                ChessFile.findByValue(String.valueOf(position.charAt(0))),
                ChessRank.findByValue(String.valueOf(position.charAt(1)))
        ));
    }

    public Set<Position> findBetween(Position target) {
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

    private Set<Position> findHorizontalPositions(List<ChessFile> betweenFiles) {
        Set<Position> positions = new HashSet<>();
        for (ChessFile file : betweenFiles) {
            positions.add(new Position(file, this.rank));
        }
        return positions;
    }

    private Set<Position> findVerticalPositions(List<ChessRank> betweenRanks) {
        Set<Position> positions = new HashSet<>();
        for (ChessRank rank : betweenRanks) {
            positions.add(new Position(this.file, rank));
        }
        return positions;
    }

    private Set<Position> findDiagonalPositions(List<ChessFile> betweenFiles, List<ChessRank> betweenRanks) {
        Set<Position> positions = new HashSet<>();
        for (int i = 0; i < betweenFiles.size(); i++) {
            positions.add(new Position(betweenFiles.get(i), betweenRanks.get(i)));
        }
        return positions;
    }

    public int calculateDistanceTo(Position target) {
        int fileDistance = calculateFileDistanceTo(target);
        int rankDistance = calculateRankDistanceTo(target);

        if (fileDistance > 0) {
            return fileDistance;
        }
        return rankDistance;
    }

    public int calculateFileDistanceTo(Position target) {
        return Math.abs(target.file.index() - file.index());
    }

    public int calculateRankDistanceTo(Position target) {
        return Math.abs(target.rank.index() - rank.index());
    }

    public boolean isRank(ChessRank rank) {
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
