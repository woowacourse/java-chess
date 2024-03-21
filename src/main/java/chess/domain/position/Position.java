package chess.domain.position;

import chess.domain.Direction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Position {
    // TODO: 8x8 사이즈 포지션 캐싱하기
    private static final Map<String, Position> CACHE = new ConcurrentHashMap<>();

    private final ChessFile file;
    private final ChessRank rank;

    private Position(ChessFile file, ChessRank rank) {
        this.file = file;
        this.rank = rank;
    }

    private Position(String position) {
        this(
                ChessFile.findByValue(String.valueOf(position.charAt(0))),
                ChessRank.findByValue(String.valueOf(position.charAt(1)))
        );
    }

    public static Position of(String position) {
        return CACHE.computeIfAbsent(position, key -> new Position(
                ChessFile.findByValue(String.valueOf(position.charAt(0))),
                ChessRank.findByValue(String.valueOf(position.charAt(1)))
        ));
    }

    // TODO : 리팩터링
    public Direction findDirectionTo(Position target) {
        if (file.value() == target.file.value() && rank.index() < target.rank.index()) {
            return Direction.TOP;
        }
        if (file.index() == target.file.index() && rank.index() > target.rank.index()) {
            return Direction.DOWN;
        }
        if (file.index() > target.file.index() && rank.index() == target.rank.index()) {
            return Direction.LEFT;
        }
        if (file.index() < target.file.index() && rank.index() == target.rank.index()) {
            return Direction.RIGHT;
        }
        if (file.index() < target.file.index() && rank.index() < target.rank.index() && calculateFileDistanceTo(target) == calculateRankDistanceTo(target)) {
            return Direction.TOP_RIGHT;
        }
        if (file.index() > target.file.index() && rank.index() < target.rank.index() && calculateFileDistanceTo(target) == calculateRankDistanceTo(target)) {
            return Direction.TOP_LEFT;
        }
        if (file.index() < target.file.index() && rank.index() > target.rank.index() && calculateFileDistanceTo(target) == calculateRankDistanceTo(target)) {
            return Direction.DOWN_RIGHT;
        }
        if (file.index() > target.file.index() && rank.index() > target.rank.index() && calculateFileDistanceTo(target) == calculateRankDistanceTo(target)) {
            return Direction.DOWN_LEFT;
        }
        throw new IllegalArgumentException("올바르지 않은 방향입니다.");
    }

    public boolean isRank(ChessRank rank) {
        return this.rank == rank;
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

    public Set<Position> findBetween(Position target) {
        Set<Position> positions = new HashSet<>();
        List<ChessRank> betweenRanks = findRankBetween(this.rank, target.rank);
        List<ChessFile> betweenFiles = findFileBetween(this.file, target.file);
        if (betweenRanks.isEmpty()) {
            for (ChessFile file : betweenFiles) {
                positions.add(new Position(file, this.rank));
            }
            return positions;
        }

        if (betweenFiles.isEmpty()) {
            for (ChessRank rank : betweenRanks) {
                positions.add(new Position(this.file, rank));
            }
            return positions;
        }

        for (int i = 0; i < betweenFiles.size(); i++) {
            positions.add(new Position(betweenFiles.get(i), betweenRanks.get(i)));
        }
        return positions;
    }

    private List<ChessRank> findRankBetween(ChessRank start, ChessRank end) {
        List<ChessRank> ranks = new ArrayList<>();
        if (start.index() < end.index()) {
            for (int index = start.index() + 1; index < end.index(); index++) {
                ranks.add(ChessRank.findByIndex(index));
            }
            return ranks;
        }
        for (int index = start.index() - 1; index > end.index(); index--) {
            ranks.add(ChessRank.findByIndex(index));
        }
        return ranks;
    }

    private List<ChessFile> findFileBetween(ChessFile start, ChessFile end) {
        List<ChessFile> files = new ArrayList<>();
        if (start.index() < end.index()) {
            for (int index = start.index() + 1; index < end.index(); index++) {
                files.add(ChessFile.findByIndex(index));
            }
            return files;
        }
        for (int index = start.index() - 1; index > end.index(); index--) {
            files.add(ChessFile.findByIndex(index));
        }
        return files;
    }
}
