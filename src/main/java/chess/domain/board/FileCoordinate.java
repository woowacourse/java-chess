package chess.domain.board;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public static final String INVALID_FILE_COORDINATE_MESSAGE = "올바른 열 번호를 입력해주세요.";

    private final int columnNumber;

    FileCoordinate(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public static FileCoordinate findBy(int columnNumber) {
        return Arrays.stream(values())
                .filter(it -> it.columnNumber == columnNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_FILE_COORDINATE_MESSAGE));
    }

    public static List<FileCoordinate> getSortedFileCoordinates() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing(FileCoordinate::getColumnNumber))
                .collect(Collectors.toList());
    }

    public int getColumnNumber() {
        return columnNumber;
    }
}
