package model.position;

import model.direction.Direction;

import java.util.Arrays;
import java.util.Objects;

public class Position {
    private static final int CHESS_BOARD_POSITION_COUNT = 64;
    private static final Position[] cache = new Position[CHESS_BOARD_POSITION_COUNT];
    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    static {
        int index = 0;
        for (File file : File.values()) {
            index = initRank(index, file);
        }
    }

    private static int initRank(int index, final File file) {
        for (Rank rank : Rank.values()) {
            cache[index++] = new Position(file, rank);
        }
        return index;
    }

    public boolean isAvailablePosition(final Direction direction) {
        return file.canMoveTo(direction) && rank.canMoveTo(direction);
    }

    public Position getNextPosition(final Direction direction) {
        return Position.of(file.moving(direction), rank.moving(direction));
    }

    public static Position of(final File file, final Rank rank) {
        return Arrays.stream(cache)
                     .filter(position -> position.file == file && position.rank == rank)
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Position 입니다."));
    }

    public File file() {
        return file;
    }

    public Rank rank() {
        return rank;
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
}
