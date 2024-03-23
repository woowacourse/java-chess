package chess.domain.position;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum File {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7);

    private final int columnNumber;

    File(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public static File from(int columnNumber) {
        return Arrays.stream(values())
                .filter(file -> file.columnNumber == columnNumber)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("해당 하는 열 위치를 찾지 못하였습니다"));
    }

    public int calculateDistanceWith(File other) {
        return Math.abs(columnNumber - other.columnNumber);
    }

    public boolean isFurtherLeftThan(File other) {
        return columnNumber < other.columnNumber;
    }

    public boolean isFurtherRightThan(File other) {
        return columnNumber > other.columnNumber;
    }

    public File move(int weight) {
        return from(columnNumber + weight);
    }
}
