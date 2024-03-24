package model.position;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import model.direction.Direction;

public class Position {
    private final int file;
    private final int rank;

    private Position(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    private static final Set<Position> cache = new HashSet<>();

    static {
        for (int file = 1; file <= 8; file++) {
            initRank(file);
        }
    }

    private static void initRank(int file) {
        for (int rank = 1; rank <= 8; rank++) {
            cache.add(new Position(file, rank));
        }
    }

    public boolean isAvailablePosition(Direction direction) {
        int movedFile = file + direction.fileDifferential();
        int movedRank = rank + direction.rankDifferential();
        return 1 <= movedFile && movedFile <= 8 && 1 <= movedRank && movedRank <= 8;
    }

    public Position getNextPosition(Direction direction){
        return Position.of(file + direction.fileDifferential(), rank + direction.rankDifferential());
    }

    public static Position of(int file, int rank) {
        return cache.stream()
                .filter(position -> position.file == file && position.rank == rank)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Position 입니다."));
    }

    public int file() {
        return file;
    }

    public int rank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position position)) {
            return false;
        }
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
