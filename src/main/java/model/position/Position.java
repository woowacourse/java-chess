package model.position;

import static util.FileConverter.A;
import static util.FileConverter.H;
import static util.RankConverter.EIGHT;
import static util.RankConverter.ONE;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import model.direction.Direction;

public class Position {
    private static final Set<Position> cache = new HashSet<>();
    private final int file;
    private final int rank;

    static {
        for (int file = A.getValue(); file <= H.getValue(); file++) {
            initRank(file);
        }
    }

    private Position(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(int file, int rank) {
        if (A.getValue() <= file && file <= H.getValue() && ONE.getValue() <= rank && rank <= EIGHT.getValue()) {
            return cache.stream()
                    .filter(position -> position.file == file && position.rank == rank)
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("캐싱된 Position 확인이 필요합니다."));
        }
        throw new IllegalArgumentException("존재하지 않는 Position 입니다.");
    }

    private static void initRank(int file) {
        for (int rank = ONE.getValue(); rank <= EIGHT.getValue(); rank++) {
            cache.add(new Position(file, rank));
        }
    }

    public boolean isAvailablePosition(Direction direction) {
        int movedFile = file + direction.fileDifferential();
        int movedRank = rank + direction.rankDifferential();
        return A.getValue() <= movedFile && movedFile <= H.getValue() && ONE.getValue() <= movedRank
                && movedRank <= EIGHT.getValue();
    }

    public Position getNextPosition(Direction direction) {
        return Position.of(file + direction.fileDifferential(), rank + direction.rankDifferential());
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
