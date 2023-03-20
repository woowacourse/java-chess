package chess.domain.path;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Movement {
    UP(0, 1),
    DOWN(0, -1),
    RIGHT(1, 0),
    LEFT(-1, 0),

    UP_RIGHT(1, 1),
    UP_LEFT(-1, 1),
    DOWN_RIGHT(1, -1),
    DOWN_LEFT(-1, -1),

    UP_UP_RIGHT(1, 2),
    UP_UP_LEFT(-1, 2),
    RIGHT_RIGHT_UP(2, 1),
    RIGHT_RIGHT_DOWN(2, -1),
    DOWN_DOWN_RIGHT(1, -2),
    DOWN_DOWN_LEFT(-1, -2),
    LEFT_LEFT_UP(-2, 1),
    LEFT_LEFT_DOWN(-2, -1);

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
}
