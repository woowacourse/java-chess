package chess.domain.position;

import chess.domain.Direction;

import java.util.Arrays;

public enum File {
    FILE_A(0),
    FILE_B(1),
    FILE_C(2),
    FILE_D(3),
    FILE_E(4),
    FILE_F(5),
    FILE_G(6),
    FILE_H(7);

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

    public File move(int distance) {
        return File.of(this.number + distance);
    }

    public Direction getDirection(File other) {
        if (other.number > this.number) {
            return Direction.PLUS;
        }
        if (other.number < this.number) {
            return Direction.MINUS;
        }
        return Direction.ZERO;
    }

    public File moveToDirection(Direction direction) {
        if (direction.equals(Direction.PLUS)) {
            return next();
        }
        if (direction.equals(Direction.MINUS)) {
            return prev();
        }
        return this;
    }

    public File next() {
        return File.of(this.number + 1);
    }

    public File prev() {
        return File.of(this.number - 1);
    }

    public int getFileIndex(){
        return number;
    }

    @Override
    public String toString() {
        return "File{" +
                "number=" + number +
                '}';
    }
}
