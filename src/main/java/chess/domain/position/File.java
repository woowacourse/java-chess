package chess.domain.position;

import chess.domain.Direction;
import java.util.Arrays;

public enum File {
    FILE_A(0, 'a'),
    FILE_B(1, 'b'),
    FILE_C(2, 'c'),
    FILE_D(3, 'd'),
    FILE_E(4, 'e'),
    FILE_F(5, 'f'),
    FILE_G(6, 'g'),
    FILE_H(7, 'h');

    public static final int ONE_SQUARE = 1;
    private final int index;
    private final char symbol;

    File(int index, char symbol) {
        this.index = index;
        this.symbol = symbol;
    }

    public static File of(int number) {
        return Arrays.stream(values())
                .filter(file -> file.index == number)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("입력한 값과 대응하는 파일이 존재하지 않습니다."));
    }

    public static File of(char symbol) {
        return Arrays.stream(values())
                .filter(file -> file.symbol == symbol)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("입력한 값과 대응하는 파일이 존재하지 않습니다."));
    }

    public File move(int distance) {
        return File.of(this.index + distance);
    }

    public int distance(File other) {
        return Math.abs(this.index - other.index);
    }

    public Direction getDirection(File other) {
        if (other.index > this.index) {
            return Direction.PLUS;
        }
        if (other.index < this.index) {
            return Direction.MINUS;
        }
        return Direction.ZERO;
    }

    public File moveOnceToDirection(Direction direction) {
        if (direction.equals(Direction.PLUS)) {
            return next();
        }
        if (direction.equals(Direction.MINUS)) {
            return prev();
        }
        return this;
    }

    public int getFileIndex() {
        return index;
    }

    private File next() {
        return File.of(this.index + ONE_SQUARE);
    }

    private File prev() {
        return File.of(this.index - ONE_SQUARE);
    }

    @Override
    public String toString() {
        return "File{" +
                "number=" + index +
                "}";
    }
}
