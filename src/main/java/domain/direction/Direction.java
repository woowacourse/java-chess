package domain.direction;

import java.util.Arrays;

public enum Direction {
    EAST(1, 0),
    WEST(-1, 0),
    SOUTH(0, -1),
    NORTH(0, 1),
    NORTHEAST(1, 1),
    NORTHWEST(-1, 1),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1),
    NORTHEAST_NORTH(1, 2),
    NORTHWEST_NORTH(-1, 2),
    NORTHWEST_WEST(-2, -1),
    SOUTHWEST_WEST(-2, 1),
    NORTHEAST_EAST(2, 1),
    SOUTHEAST_EAST(2, -1),
    SOUTHEAST_SOUTH(1, -2),
    SOUTHWEST_SOUTH(-1, -2),
    SOUTH_SOUTH(0, -2),
    NORTH_NORTH(0, 2);

    private final int file;
    private final int rank;

    Direction(final int file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Direction valueOf(final int file, final int rank) {
        return Arrays.stream(Direction.values())
            .filter(direction -> direction.file  == file)
            .filter(direction -> direction.rank == rank)
            .findFirst()
            .orElseThrow(()->new IllegalArgumentException("[ERROR] 존재하지 않는 이동 방향입니다.")) ;
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }
}
