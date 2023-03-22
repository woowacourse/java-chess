package chess.domain.position;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum FileCoordinate {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8),
    ;

    private static final int SKIP_MY_COORDINATE = 1;

    private final int columnNumber;

    FileCoordinate(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public static FileCoordinate findBy(int columnNumber) {
        return Arrays.stream(values())
                .filter(it -> it.columnNumber == columnNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 열 번호를 입력해주세요."));
    }

    public List<FileCoordinate> betweenFiles(FileCoordinate other) {
        List<FileCoordinate> result = IntStream.range(
                        min(columnNumber, other.columnNumber),
                        max(columnNumber, other.columnNumber))
                .skip(SKIP_MY_COORDINATE)
                .mapToObj(FileCoordinate::findBy)
                .collect(Collectors.toList());
        if (columnNumber > other.columnNumber) {
            Collections.reverse(result);
        }
        return result;
    }

    public int calculateGap(final FileCoordinate other) {
        return columnNumber - other.columnNumber;
    }
}
