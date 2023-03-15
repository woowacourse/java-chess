package chess.domain.position;

import chess.domain.Direction;

import java.util.Arrays;

public enum File {
    FILE_A(1),
    FILE_B(2),
    FILE_C(3),
    FILE_D(4),
    FILE_E(5),
    FILE_F(6),
    FILE_G(7),
    FILE_H(8);

    private final int number;

    File(int number) {
        this.number = number;
    }

    public static File of(int number) {
        return Arrays.stream(values())
                .filter(file -> file.number == number).findFirst().get();
    }

    public int distance(File other) {
        return Math.abs(this.number - other.number);
    }

    public Direction getDirection(File other) {
        if (other.number - this.number > 0) {
            return Direction.PLUS;
        }
        return Direction.MINUS;
    }

    public File moveToDirection(Direction direction) {
        if (direction.equals(Direction.PLUS)) {
            return next();
        }
        return prev();
    }

    public File next() {
        return File.of(this.number + 1);
    }

    public File prev() {
        return File.of(this.number - 1);
    }

}
