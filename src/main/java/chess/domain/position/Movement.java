package chess.domain.position;

import java.util.Arrays;

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

    private static final String MOVEMENT_NOT_FOUND_MESSAGE = "이동할 수 없는 방법입니다.";

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
                .orElseThrow(() -> new IllegalArgumentException(MOVEMENT_NOT_FOUND_MESSAGE));
    }

    public Position nextPosition(final File file, final Rank rank) {
        return new Position(file.value() + this.file, rank.value() + this.rank);
    }
}
