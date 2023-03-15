package chess.domain.board;

import java.util.Arrays;

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

    private final int columnNumber;

    FileCoordinate(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public static FileCoordinate findBy(int columnNumber) {
        return Arrays.stream(values())
                .filter(it -> it.getColumnNumber() == columnNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 열 번호를 입력해주세요."));
    }

    public int getColumnNumber() {
        return columnNumber;
    }
}
