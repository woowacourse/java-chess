package model.position;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import model.direction.Direction;

public class Position {
    public static final int MIN_POSITION = 1;
    public static final int MAX_POSITION = 8;
    private final int file;
    private final int rank;

    private Position(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    private static final Set<Position> cache = new HashSet<>();

    static {
        for (int file = MIN_POSITION; file <= MAX_POSITION; file++) {
            initRank(file);
        }
    }

    private static void initRank(int file) {
        for (int rank = MIN_POSITION; rank <= MAX_POSITION; rank++) {
            cache.add(new Position(file, rank));
        }
    }

    public boolean isAvailablePosition(Direction direction) {
        int movedFile = file + direction.fileDifferential();
        int movedRank = rank + direction.rankDifferential();
        return MIN_POSITION <= movedFile && movedFile <= MAX_POSITION && MIN_POSITION <= movedRank
                && movedRank <= MAX_POSITION;
    }

    public Position getNextPosition(Direction direction) {
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
