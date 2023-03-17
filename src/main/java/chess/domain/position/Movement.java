package chess.domain.position;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Movement {
    U(0, 1),
    D(0, -1),
    R(1, 0),
    L(-1, 0),

    UR(1, 1),
    UL(-1, 1),
    DR(1, -1),
    DL(-1, -1),

    UUR(1, 2),
    UUL(-1, 2),
    RRU(2, 1),
    RRD(2, -1),
    DDR(1, -2),
    DDL(-1, -2),
    LLU(-2, 1),
    LLD(-2, -1);

    private final int file;
    private final int rank;

    Movement(final int file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Movement of(final int file, final int rank) {
        return Arrays.stream(Movement.values())
                .filter(movement -> movement.file == file && movement.rank == rank)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("이동할 수 없는 방향입니다."));
    }

    public Position nextPosition(File file, Rank rank) {
        return new Position(file.value() + this.file, rank.value() + this.rank);
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }
}
